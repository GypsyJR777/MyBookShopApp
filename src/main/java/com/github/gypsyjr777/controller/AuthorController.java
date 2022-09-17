package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.author.Author;
import com.github.gypsyjr777.entity.author.AuthorBio;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.AuthorService;
import com.github.gypsyjr777.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
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

    @GetMapping("/{authorId}")
    public String authorsSlugPage(@PathVariable(name = "authorId") Integer authorId, Model model) {
        Author author = authorService.getAuthorById(authorId);

        model.addAttribute("author", author);
        model.addAttribute("bio", new AuthorBio(author.getDescription()));
        model.addAttribute("booksList", bookService.getPageBooksByAuthor(authorId, 0, 20).getBooks());

        return "authors/slug";
    }

    @GetMapping("/page/{authorId}")
    @ResponseBody
    public BooksCount getBooksPage(@PathVariable(name = "authorId") Integer authorId,
                                   @RequestParam("offset") Integer offset,
                                   @RequestParam("limit") Integer limit) {
        return bookService.getPageBooksByAuthor(authorId, offset, limit);
    }
}
