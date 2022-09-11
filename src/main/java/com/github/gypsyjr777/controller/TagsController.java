package com.github.gypsyjr777.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tags")
public class TagsController {
    @GetMapping("/SLUG")
    public String tagPage() {
        return "tags/index";
    }
}
