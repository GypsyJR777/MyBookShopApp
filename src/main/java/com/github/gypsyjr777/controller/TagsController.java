package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import com.github.gypsyjr777.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tags")
public class TagsController {
    private final TagService tagService;
    private final BookService bookService;

    @Autowired
    public TagsController(TagService tagService, BookService bookService) {
        this.tagService = tagService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/{tag_id}")
    public String tagPage(@PathVariable Integer tag_id, Model model) {
        model.addAttribute("booksList", bookService.getPageBooksByTag(tag_id, 0, 20).getBooks());
        model.addAttribute("tag", tagService.getTagById(tag_id));
        return "tags/index";
    }

    @GetMapping("/page/{tag_id}")
    @ResponseBody
    public BooksCount getNextTagPage(@PathVariable(name = "tag_id", required = false) Integer tag_id,
                                     @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {

        return bookService.getPageBooksByTag(tag_id, offset, limit);
    }
}
