package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("")
    public String authorsPage(Model model) {
        model.addAttribute("authors", authorService.getMapAuthorsAndFirstLetters());
        return "authors/index";
    }

    @GetMapping("/SLUG")
    public String authorsSlugPage(Model model) {
        return "authors/slug";
    }
}
