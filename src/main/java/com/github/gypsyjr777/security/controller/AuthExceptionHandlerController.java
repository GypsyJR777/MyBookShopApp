package com.github.gypsyjr777.security.controller;

import com.github.gypsyjr777.security.errs.NoUserFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.message.AuthException;
import java.util.logging.Logger;

@ControllerAdvice
public class AuthExceptionHandlerController {
    @ExceptionHandler(AuthException.class)
    public String handleAuthException(AuthException e, RedirectAttributes redirectAttributes) {
        Logger.getLogger(this.getClass().getSimpleName()).warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("searchError", e);
        return "redirect:/";
    }

    @ExceptionHandler(NoUserFoundException.class)
    public String handleNoUserFoundException(NoUserFoundException e, RedirectAttributes redirectAttributes) {
        Logger.getLogger(this.getClass().getSimpleName()).warning(e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("searchError", e);
        return "redirect:/";
    }
}
