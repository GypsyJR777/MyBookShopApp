package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
