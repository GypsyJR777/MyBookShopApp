package com.github.GypsyJR777.MyBookShopApp.controller;

import com.github.GypsyJR777.MyBookShopApp.entity.book.BooksCount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiController {
    @PostMapping ("/requestContactConfirmation")
    public Object requestContactConfirmation() {
        //TODO дописать
        return new Object();
    }

    @PostMapping ("/approveContact")
    public Object approveContact() {
        //TODO дописать
        return new Object();
    }

    @PostMapping ("/bookReview")
    public Object bookReview() {
        //TODO дописать
        return new Object();
    }

    @PostMapping ("/changeBookStatus")
    public Object changeBookStatus() {
        //TODO дописать
        return new Object();
    }

    @PostMapping ("/rateBookReview")
    public Object rateBookReview() {
        //TODO дописать
        return new Object();
    }

    @PostMapping ("/rateBook")
    public Object rateBook() {
        //TODO дописать
        return new Object();
    }

    @PostMapping ("/payment")
    public Object payment() {
        //TODO дописать
        return new Object();
    }

    @GetMapping ("/transactions")
    public Object getTransactions() {
        //TODO дописать
        return new Object();
    }

    @GetMapping ("/search")
    public BooksCount searchBooks() {
        //TODO дописать
        return new BooksCount();
    }
}
