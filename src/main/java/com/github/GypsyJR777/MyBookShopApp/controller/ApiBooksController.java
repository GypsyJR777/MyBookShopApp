package com.github.GypsyJR777.MyBookShopApp.controller;

import com.github.GypsyJR777.MyBookShopApp.entity.BooksCount;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/books")
public class ApiBooksController {
    @GetMapping("/recommended")
    public BooksCount getRecommended() {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/recent")
    public BooksCount getRecent() {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/popular")
    public BooksCount getPopular() {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/genre/{id}")
    public BooksCount getByGenre(@PathVariable int id) {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/author/{id}")
    public BooksCount getByAuthor(@PathVariable int id) {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/tag/{id}")
    public BooksCount getByTag(@PathVariable int id) {
        // TODO дописать
        return new BooksCount();
    }
}
