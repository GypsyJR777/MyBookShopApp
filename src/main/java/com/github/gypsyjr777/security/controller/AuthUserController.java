package com.github.gypsyjr777.security.controller;

import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.security.model.ContactConfirmationPayload;
import com.github.gypsyjr777.security.model.ContactConfirmationResponse;
import com.github.gypsyjr777.security.model.RegistrationForm;
import com.github.gypsyjr777.security.service.JWTBlacklistService;
import com.github.gypsyjr777.security.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthUserController {
    private final RegistrationService registrationService;
    private final JWTBlacklistService jwtBlacklistService;

    @Autowired
    public AuthUserController(RegistrationService registrationService, JWTBlacklistService jwtBlacklistService) {
        this.registrationService = registrationService;
        this.jwtBlacklistService = jwtBlacklistService;
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
