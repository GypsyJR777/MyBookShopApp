package com.github.GypsyJR777.MyBookShopApp.controller;

import com.github.GypsyJR777.MyBookShopApp.entity.Book;
import com.github.GypsyJR777.MyBookShopApp.service.AuthorService;
import com.github.GypsyJR777.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainPageController {
    private final BookService bookService;

    @Autowired
    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookService.getBooksData();
    }

    @GetMapping("/")
    public String mainPage() {

        return "index";
    }
}
