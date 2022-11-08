package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BooksRateController {
    private final BookService bookService;

    @Autowired
    public BooksRateController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/rateBook")
    public String rateBook(@RequestParam(value = "bookId") Integer bookId,
                           @RequestParam(value = "bookSlug") String bookSlug,
                           @RequestParam(value = "value") Integer value) {
        bookService.saveRateToBook(bookId, value);
        return "redirect:/books/" + bookSlug;
    }
}
