package com.github.gypsyjr777.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenresController {

    @GetMapping("")
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping("/SLUG")
    public String genresSlugPage() {
        return "genres/slug";
    }
}
