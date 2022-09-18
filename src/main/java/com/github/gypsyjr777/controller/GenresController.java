package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.genre.GenreEntity;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import com.github.gypsyjr777.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenresController {
    private final GenreService genreService;
    private final BookService bookService;

    @Autowired
    public GenresController(GenreService genreService, BookService bookService) {
        this.genreService = genreService;
        this.bookService = bookService;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("genres")
    public List<GenreEntity> getGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping
    public String genresPage() {
        return "genres/index";
    }

    @GetMapping("/{genreId}")
    public String bookGenresPage(@PathVariable(name = "genreId") Integer genreId, Model model) {
        model.addAttribute("booksList", bookService.getPageBooksByGenre(genreId, 0, 20).getBooks());
        model.addAttribute("genre", genreService.getGenreById(genreId));
        return "genres/slug";
    }

    @GetMapping("/page/{genreId}")
    @ResponseBody
    public BooksCount nextBookGenresPage(@PathVariable(name = "genreId") Integer genreId,
                                         @RequestParam("offset") Integer offset,
                                         @RequestParam("limit") Integer limit) {
        return bookService.getPageBooksByGenre(genreId, offset, limit);
    }
}
