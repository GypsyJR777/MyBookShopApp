package com.github.GypsyJR777.MyBookShopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/signin")
    public String signInPage() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/postponed")
    public String postponedPage() {
        return "postponed";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

//    @GetMapping("/transactions")
//    public String transactionsPage() {
//        return "transactions";
//    }
//
//    @GetMapping("/payment")
//    public String paymentPage() {
//        return "payment";
//    }

    @GetMapping("/my")
    public String myBooksPage() {
        return "my";
    }
}
