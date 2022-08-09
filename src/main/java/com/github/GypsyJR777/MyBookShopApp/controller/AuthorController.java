package com.github.GypsyJR777.MyBookShopApp.controller;

import com.github.GypsyJR777.MyBookShopApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
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
