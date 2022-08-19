package com.github.GypsyJR777.MyBookShopApp.controller;

import com.github.GypsyJR777.MyBookShopApp.entity.Book;
import com.github.GypsyJR777.MyBookShopApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/author/SLUG")
    public String authorSlugPage() {
        return "books/author";
    }

    @GetMapping("/SLUG")
    public String bookSlugPage() {
        return "books/slug";
    }
}
