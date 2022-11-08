package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class ReviewBookController {
    private BookService bookService;

    @Autowired
    public ReviewBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addreview/{slug}")
    public String handleAddReviewBook(@PathVariable String slug, @RequestParam("bookReview") String bookReview) {
        bookService.addReview(slug, bookReview);
        return "redirect:/books/" + slug;
    }
}
