package com.github.gypsyjr777.security.controller;

import com.github.gypsyjr777.config.EmailConfig;
import com.github.gypsyjr777.entity.payments.TransactionCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.security.entity.UserCode;
import com.github.gypsyjr777.security.model.*;
import com.github.gypsyjr777.security.service.*;
import com.github.gypsyjr777.service.PaymentService;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class UserController {
    private final RegistrationService registrationService;
    private final JWTBlacklistService jwtBlacklistService;
    private final JavaMailSender javaMailSender;
    private final EmailConfig emailConfig;
    private final CodeService codeService;
    private final SmsService smsService;
    private final BalanceService balanceService;

    @Autowired
    public UserController(RegistrationService registrationService, JWTBlacklistService jwtBlacklistService,
                          JavaMailSender javaMailSender, EmailConfig emailConfig, CodeService codeService,
                          SmsService smsService, BalanceService balanceService) {
        this.registrationService = registrationService;
        this.jwtBlacklistService = jwtBlacklistService;
        this.javaMailSender = javaMailSender;
        this.emailConfig = emailConfig;
        this.codeService = codeService;
        this.smsService = smsService;
        this.balanceService = balanceService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/signin")
    public String handleSignIn() {
        return "signin";
    }

    @GetMapping("/signup")
    public String handleSignUp(Model model) {
        model.addAttribute("regForm", new RegistrationForm());
        return "signup";
    }

    @PostMapping("/requestContactConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestContactConfirmation(@RequestBody ContactConfirmationPayload contactConfirmationPayload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");

        if (contactConfirmationPayload.getContact().contains("@")) {
            return response;
        } else {
//            String smsCodeString = smsService.sendSecretCodeSms(contactConfirmationPayload.getContact());
//            smsService.saveNewCode(new UserCode(smsCodeString, 60)); //expires in 1 min.
            return response;
        }
    }

    @PostMapping("/requestEmailConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestEmailContactConfirmation(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmail());
        message.setTo(payload.getContact());
        UserCode smsCode = new UserCode(codeService.generateCode(), 300); //5 minutes
        codeService.saveNewCode(smsCode);
        message.setSubject("Bookstore email verification!");
        message.setText("Verification code is: " + smsCode.getCode());
        javaMailSender.send(message);
        response.setResult("true");
        return response;
    }

    @PostMapping("/reg")
    public String handleUserRegistration(RegistrationForm registrationForm, Model model) {
        registrationService.registerNewUser(registrationForm);
        model.addAttribute("regOk", true);
        return "signin";
    }

    @PostMapping("/approveContact")
    @ResponseBody
    public ContactConfirmationResponse handleApproveContact(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();

        if (codeService.verifyCode(payload.getCode())) {
            response.setResult("true");
        }

        response.setResult("true");

        return response;
    }

    @PostMapping("/login")
    @ResponseBody
    public ContactConfirmationResponse handleLogin(@RequestBody ContactConfirmationPayload payload,
                                                   HttpServletResponse httpServletResponse) {
        ContactConfirmationResponse loginResponse = registrationService.jwtLogin(payload);
        Cookie cookie = new Cookie("token", loginResponse.getResult());
        httpServletResponse.addCookie(cookie);
        return loginResponse;
    }

    @PostMapping("/login-by-phone-number")
    @ResponseBody
    public ContactConfirmationResponse handleLoginByPhoneNumber(@RequestBody ContactConfirmationPayload payload,
                                                                HttpServletResponse httpServletResponse) {
        if(smsService.verifyCode(payload.getCode())) {
            ContactConfirmationResponse loginResponse = registrationService.jwtLoginByPhoneNumber(payload);
            Cookie cookie = new Cookie("token", loginResponse.getResult());
            httpServletResponse.addCookie(cookie);
            return loginResponse;
        }else {
            return null;
        }
    }

    @GetMapping("/my")
    public String myBooksPage(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("token") && jwtBlacklistService.isTokenOld(cookie.getValue())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);

                response.addCookie(cookie);
                return "redirect:/signin";
            }
        }

        return "my";
    }

    @GetMapping("/profile")
    public String handleProfile(Model model) {

        model.addAttribute("curUsr", registrationService.getCurrentUser());
        model.addAttribute("userTransactions", registrationService.getTransactions(0, 10));
        return "profile";
    }

    @PostMapping("/profile")
    public String handleChangeProfile(Model model, ProfileDetail profileDetail) {
        registrationService.changeDataUser(profileDetail);
        model.addAttribute("curUsr", registrationService.getCurrentUser());
        return "profile";
    }

    @GetMapping("/conformationCheck")
    public String confirmRegistration(
            WebRequest request, Model model, @RequestParam("token") String token) {
        registrationService.changeDataUser(token);
        return "redirect:/profile";
    }

    @PostMapping(value = "/refill")
    public RedirectView refillBalance(PaymentPayload payload) throws NoSuchAlgorithmException {
        BookstoreUser user = (BookstoreUser) registrationService.getCurrentUser();
        String paymentUrl = balanceService.getPaymentUrl(payload, user.getId());
        return new RedirectView(paymentUrl);
    }

    @GetMapping("/refill/result")
    public String refillBalanceOk(Jws<RobokassaResult> result) {
        BookstoreUser user = (BookstoreUser) registrationService.getCurrentUser();
        if (balanceService.checkResult(result) && user.getId() == result.getBody().getInvId()) {
            user.addBalance(result.getBody().getIncSum());
            registrationService.transactionLog(user, "Пополнение счета на сумму: " + result.getBody().getIncSum(), result.getBody().getIncSum());
            registrationService.updateBalanceUser(user);
        }
        return "redirect:/profile";
    }

    @GetMapping("/transactions")
    public TransactionCount refillBalanceOk(@RequestParam("sort") String sort, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new TransactionCount(registrationService.getTransactions(offset, limit));
    }
//    @GetMapping("/logout")
//    public String handleLogout(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        SecurityContextHolder.clearContext();
//        if (session != null) {
//            session.invalidate();
//        }
//
//        for (Cookie cookie : request.getCookies()) {
//            if (cookie.getName().equals("token")) {
//                jwtBlacklistService.addToken(cookie.getValue());
//            }
//            cookie.setMaxAge(0);
//        }
//
//        return "redirect:/";
//    }
}
