package com.github.gypsyjr777.security.controller;

import com.github.gypsyjr777.config.EmailConfig;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.security.entity.UserCode;
import com.github.gypsyjr777.security.model.ContactConfirmationPayload;
import com.github.gypsyjr777.security.model.ContactConfirmationResponse;
import com.github.gypsyjr777.security.model.RegistrationForm;
import com.github.gypsyjr777.security.service.CodeService;
import com.github.gypsyjr777.security.service.JWTBlacklistService;
import com.github.gypsyjr777.security.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthUserController {
    private final RegistrationService registrationService;
    private final JWTBlacklistService jwtBlacklistService;
    private final JavaMailSender javaMailSender;
    private final EmailConfig emailConfig;
    private final CodeService codeService;

    @Autowired
    public AuthUserController(RegistrationService registrationService, JWTBlacklistService jwtBlacklistService,
                              JavaMailSender javaMailSender, EmailConfig emailConfig, CodeService codeService) {
        this.registrationService = registrationService;
        this.jwtBlacklistService = jwtBlacklistService;
        this.javaMailSender = javaMailSender;
        this.emailConfig = emailConfig;
        this.codeService = codeService;
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
        return response;
    }

    @PostMapping("/requestEmailConfirmation")
    @ResponseBody
    public ContactConfirmationResponse handleRequestEmailContactConfirmation(@RequestBody ContactConfirmationPayload payload) {
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmail());
        message.setTo(payload.getContact());
        UserCode smsCode = new UserCode(codeService.generateCode(),300); //5 minutes
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

        if(codeService.verifyCode(payload.getCode())){
            response.setResult("true");
        }

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

    @GetMapping("/my")
    public String myBooksPage(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie: request.getCookies()) {
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
        return "profile";
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
