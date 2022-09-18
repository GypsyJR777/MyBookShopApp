package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books/popular")
public class PopularController {
    private final BookService bookService;

    @Autowired
    public PopularController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("booksList")
    public List<Book> bookList() {
        return bookService.getPageOfPopularBooks(0, 20).getBooks();
    }

    @GetMapping
    public String popularBookPage() {
        return "books/popular";
    }

    @GetMapping("/page")
    @ResponseBody
    public BooksCount nextPopularBookPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit) {
        return bookService.getPageOfPopularBooks(offset, limit);
    }
}
