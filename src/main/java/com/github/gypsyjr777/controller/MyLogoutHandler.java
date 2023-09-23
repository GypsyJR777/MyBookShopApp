package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.security.service.JWTBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class MyLogoutHandler implements LogoutHandler {
    private final JWTBlacklistService jwtBlacklistService;

    @Autowired
    public MyLogoutHandler(JWTBlacklistService jwtBlacklistService) {
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        for (Cookie cookie : httpServletRequest.getCookies()) {
            if (cookie.getName().equals("token")) {
                jwtBlacklistService.addToken(cookie.getValue());
            }
        }
    }
}
