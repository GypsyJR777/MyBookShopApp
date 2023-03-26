package com.github.gypsyjr777.utils;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.service.BookService;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.List;

public class Utils {
    public static List<Book> substringStartAndEnd(@CookieValue(value = "cartContents", required = false) String cartContents, BookService bookService) {
        cartContents = cartContents.startsWith("/") ? cartContents.substring(1) : cartContents;
        cartContents = cartContents.endsWith("/") ? cartContents.substring(0, cartContents.length() - 1) : cartContents;

        String[] cookieSlugs = cartContents.split("/");

        return bookService.getBooksBySlugs(cookieSlugs);
    }
}
