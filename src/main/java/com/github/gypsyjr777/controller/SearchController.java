package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.errs.EmptySearchException;
import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final BookService bookService;

    @Autowired
    public SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("booksList")
    public List<Book> searchResults() {
        return new ArrayList<>();
    }

    @GetMapping({"", "/{searchWordDto}"})
    public String getSearchResults(@PathVariable(value = "searchWordDto", required = false) SearchWordDto searchWord, Model model) throws EmptySearchException {
        if (searchWord != null) {
            model.addAttribute("searchWordDto", searchWord);
            model.addAttribute(
                    "booksList",
                    bookService.getPageOfSearchResultBooks(searchWord.getExample(), 0, 5).getContent()
            );

            return "search/index";
        } else {
            throw new EmptySearchException("Поиск по null невозможен");
        }
    }

    @GetMapping("/page/{searchWordDto}")
    @ResponseBody
    public BooksCount getNextSearchPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit,
                                        @PathVariable(value = "searchWordDto", required = false) SearchWordDto searchWord) {
        return new BooksCount(bookService.getPageOfSearchResultBooks(searchWord.getExample(), offset, limit).getContent());
    }
}
