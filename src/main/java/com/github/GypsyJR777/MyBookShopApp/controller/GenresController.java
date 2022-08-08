package com.github.GypsyJR777.MyBookShopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresController {

    @GetMapping("/genres")
    public String genresPage() {
        return "genres/index";
    }
}
