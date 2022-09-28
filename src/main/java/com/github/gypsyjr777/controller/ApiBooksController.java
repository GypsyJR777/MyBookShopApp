package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.errs.BookstoreApiWrongParameterException;
import com.github.gypsyjr777.model.ApiResponse;
import com.github.gypsyjr777.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/books")
@Api(description = "book data api")
public class ApiBooksController {

    private final BookService bookService;

    @Autowired
    public ApiBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/recommended")
    public BooksCount getRecommended() {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/recent")
    public BooksCount getRecent() {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/popular")
    public BooksCount getPopular() {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/genre/{id}")
    public BooksCount getByGenre(@PathVariable int id) {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/author/{id}")
    public BooksCount getByAuthor(@PathVariable int id) {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/tag/{id}")
    public BooksCount getByTag(@PathVariable int id) {
        // TODO дописать
        return new BooksCount();
    }

    @GetMapping("/by-author")
    @ApiOperation("operation to get book list of bookshop by passed author first name")
    public ResponseEntity<List<Book>> booksByAuthor(@RequestParam("author") String authorName) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorName));
    }

    @GetMapping("/by-title")
    @ApiOperation("get books by title")
    public ResponseEntity<ApiResponse<Book>> booksByTitle(@RequestParam("title") String title) throws BookstoreApiWrongParameterException {
        ApiResponse<Book> response = new ApiResponse<>();
        List<Book> data = bookService.getBooksByTitle(title);
        response.setDebugMessage("successful request");
        response.setMessage("data size: " + data.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimestamp(LocalDateTime.now());
        response.setData(data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-price-range")
    @ApiOperation("get books by price range from min price to max price")
    public ResponseEntity<List<Book>> priceRangeBookss(@RequestParam("min") Integer min, @RequestParam("max") Integer max) {
        return ResponseEntity.ok(bookService.getBooksWithPriceBetween(min, max));
    }

    @GetMapping("/with-max-discount")
    @ApiOperation("get list of book with max price")
    public ResponseEntity<List<Book>> maxPriceBooks() {
        return ResponseEntity.ok(bookService.getBooksWithMaxPrice());
    }

    @GetMapping("/bestsellers")
    @ApiOperation("get bestseller book (which is_bestseller = 1)")
    public ResponseEntity<List<Book>> bestSellerBooks() {
        return ResponseEntity.ok(bookService.getBestsellers());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Book>> handleMissingServletRequestParameterException(Exception exception) {
        return new ResponseEntity<>(new ApiResponse<Book>(HttpStatus.BAD_REQUEST, "Missing required parameter",
                exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookstoreApiWrongParameterException.class)
    public ResponseEntity<ApiResponse<Book>> handleBookstoreApiWrongParameterException(Exception exception) {
        return new ResponseEntity<>(new ApiResponse<Book>(HttpStatus.BAD_REQUEST, "Bad parameter value...",
                exception), HttpStatus.BAD_REQUEST);
    }
}
