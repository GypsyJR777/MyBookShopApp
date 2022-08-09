package com.github.GypsyJR777.MyBookShopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {
    @GetMapping("/recent")
    public String recentPage() {
        return "books/recent";
    }

    @GetMapping("/popular")
    public String popularPage() {
        return "books/popular";
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
