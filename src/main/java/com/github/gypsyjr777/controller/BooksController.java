package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.search.SearchWordDto;
import com.github.gypsyjr777.service.BookService;
import com.github.gypsyjr777.service.ResourceStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final ResourceStorage resourceStorage;

    @Autowired
    public BooksController(BookService bookService, ResourceStorage resourceStorage) {
        this.bookService = bookService;
        this.resourceStorage = resourceStorage;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping("/author/{authorId}")
    public String bookSlugPage(@PathVariable(name = "authorId") Integer authorId, Model model) {
        model.addAttribute("booksList", bookService.getPageBooksByAuthor(authorId, 0, 20).getBooks());
        return "books/author";
    }

    @GetMapping("/{slug}")
    public String bookSlugPage(@PathVariable String slug, Model model) {
        model.addAttribute("slugBook", bookService.getBookBySlug(slug));
        return "books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file") MultipartFile file,
                                   @PathVariable("slug") String slug) throws IOException {
        String savePath = resourceStorage.saveNewBookImageBySlug(file, slug);
        Book bookToUpdate = bookService.getBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookService.saveBook(bookToUpdate); //save new path in db here

        return "redirect:/books/" + slug;
    }

    @GetMapping("/recommended")
    @ResponseBody
    public BooksCount getBooksPage(@RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new BooksCount(bookService.getPageOfRecommendedBooks(offset, limit).getContent());
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable String hash) throws IOException {
        Path path = resourceStorage.getBookFilePath(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file path: " + path);

        MediaType mediaType = resourceStorage.getBookFileMime(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = resourceStorage.getBookFileByteArray(hash);
        Logger.getLogger(this.getClass().getSimpleName()).info("book file data len: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }
}
