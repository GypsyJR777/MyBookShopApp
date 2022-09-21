package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books/recent")
public class RecentController {
    private final BookService bookService;

    public RecentController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("booksList")
    public List<Book> recentBooks() {
        LocalDate dateTo = LocalDate.now();
        LocalDate dateFrom = dateTo.minusMonths(1);
        return bookService.getPageOfRecentBooks(dateFrom, dateTo, 0, 20).getBooks();
    }

    @GetMapping
    public String recentBookPage() {
        return "books/recent";
    }

    @GetMapping("/page")
    @ResponseBody
    public BooksCount getNextRecentBookPage(@RequestParam("offset") Integer offset,
                                            @RequestParam("limit") Integer limit,
                                            @RequestParam(value = "from", required = false) String from,
                                            @RequestParam(value = "to", required = false) String to) {
        if (from == null && to == null) {
            LocalDate dateTo = LocalDate.now();
            LocalDate dateFrom = dateTo.minusYears(1);
            return bookService.getPageOfRecentBooks(dateFrom, dateTo, offset, limit);
        }

        return bookService.getPageOfRecentBooks(from, to, offset, limit);
    }
}
