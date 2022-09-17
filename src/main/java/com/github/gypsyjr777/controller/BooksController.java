package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/author/{authorId}")
    public String bookSlugPage(@PathVariable(name = "authorId") Integer authorId, Model model) {
        model.addAttribute("booksList", bookService.getPageBooksByAuthor(authorId, 0, 20).getBooks());
        return "books/author";
    }

    @GetMapping("/SLUG")
    public String bookSlugPage() {
        return "books/slug";
    }

    @GetMapping("/recommended")
    @ResponseBody
    public BooksCount getBooksPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksCount(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }
}
