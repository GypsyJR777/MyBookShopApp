package com.github.gypsyjr777.security.controller;

import com.github.gypsyjr777.security.errs.NoUserFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.message.AuthException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@ControllerAdvice
public class AuthExceptionHandlerController {
    @ExceptionHandler(AuthException.class)
    public String handleAuthException(AuthException e, RedirectAttributes redirectAttributes) {
        Logger.getLogger(this.getClass().getSimpleName()).warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("authError", e);
        return "redirect:/";
    }

    @ExceptionHandler(NoUserFoundException.class)
    public String handleNoUserFoundException(NoUserFoundException e, RedirectAttributes redirectAttributes) {
        Logger.getLogger(this.getClass().getSimpleName()).warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("userFoundError", e);
        return "redirect:/";
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public String handleJwtException(ExpiredJwtException e, RedirectAttributes redirectAttributes,
                                     HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("token")) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);

                response.addCookie(cookie);
                return "redirect:/signin";
            }
        }
        Logger.getLogger(this.getClass().getSimpleName()).warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("jwtError", e);
        return "redirect:/";
    }
}
