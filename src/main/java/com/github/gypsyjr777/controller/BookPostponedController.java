package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import com.github.gypsyjr777.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("/books")
public class BookPostponedController {
    private final BookService bookService;

    @Autowired
    public BookPostponedController(BookService bookService) {
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute(name = "bookPostponed")
    public List<Book> bookPostponed() {
        return new ArrayList<>();
    }

    @PostMapping("/changeBookStatus/postponed/{slug}")
    public String handleAddBookToPostponed(@PathVariable String slug,
                                           @CookieValue(name = "postponedContents", required = false) String postponedContents,
                                           HttpServletResponse response, Model model) {
        if (postponedContents == null || postponedContents.equals("")) {
            Cookie cookie = new Cookie("postponedContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else if (!postponedContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");
            stringJoiner.add(postponedContents).add(slug);

            Cookie cookie = new Cookie("postponedContents", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        }

        return "redirect:/books/" + slug;
    }

    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public String handleRemoveBookToPostponed(@PathVariable String slug,
                                              @CookieValue(name = "postponedContents", required = false) String postponedContents,
                                              HttpServletResponse response, Model model) {
        if (postponedContents != null && !postponedContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(postponedContents.split("/")));
            cookieBooks.remove(slug);

            Cookie cookie = new Cookie("postponedContents", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }

        return "redirect:/books/postponed";
    }

    @GetMapping("/postponed")
    public String handlePostponedRequest(@CookieValue(value = "postponedContents", required = false) String postponedContents,
                                         Model model) {
        if (postponedContents == null || postponedContents.equals("")) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            List<Book> booksFromCookiesSlug = Utils.substringStartAndEnd(postponedContents, bookService);

            model.addAttribute("bookPostponed", booksFromCookiesSlug);
        }

        return "postponed";
    }
}
