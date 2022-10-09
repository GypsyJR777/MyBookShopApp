package com.github.gypsyjr777.service;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.BooksCount;
import com.github.gypsyjr777.entity.book.rate.BookRate;
import com.github.gypsyjr777.errs.BookstoreApiWrongParameterException;
import com.github.gypsyjr777.repository.BookRateRepository;
import com.github.gypsyjr777.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private final String DATE_PATTERN = "dd.MM.yyyy";
    private final BookRepository bookRepository;
    private final BookRateRepository bookRateRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookRateRepository bookRateRepository) {
        this.bookRepository = bookRepository;
        this.bookRateRepository = bookRateRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if (title.length() <= 1) {
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<Book> data = bookRepository.findBooksByTitleContaining(title);

            if (data.size() > 0) {
                return data;
            } else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceOldBetween(min, max);
    }

    public List<Book> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    public BooksCount getPageOfRecentBooks(String from, String to, Integer offset, Integer limit) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        Pageable nextPage = PageRequest.of(offset, limit);
        LocalDate dateFrom = LocalDate.parse(from, dateTimeFormatter);
        LocalDate dateTo = LocalDate.parse(to, dateTimeFormatter);

        return new BooksCount(bookRepository.findBookByPubDateBetweenOrderByPubDateDesc(dateFrom, dateTo, nextPage).getContent());
    }

    public BooksCount getPageOfRecentBooks(LocalDate from, LocalDate to, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        return new BooksCount(bookRepository.findBookByPubDateBetweenOrderByPubDateDesc(from, to, nextPage).getContent());
    }

    public BooksCount getPageOfPopularBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);

        return new BooksCount(bookRepository.findBookByPopularity(nextPage).getContent());
    }

    public BooksCount getPageBooksByTag(Integer tagId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new BooksCount(bookRepository.findBookByIdInTag(tagId, nextPage).getContent());
    }

    public BooksCount getPageBooksByGenre(Integer genreId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new BooksCount(bookRepository.findBookByIdInGenre(genreId, nextPage).getContent());
    }

    public BooksCount getPageBooksByAuthor(Integer authorId, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return new BooksCount(bookRepository.findBookByAuthorId(authorId, nextPage).getContent());
    }

    public Book getBookBySlug(String slug) {
        return bookRepository.findBookBySlug(slug);
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> getBooksBySlugs(String[] slugs){
        return bookRepository.findBooksBySlugIn(slugs);
    }

    public void saveRateToBook(Integer bookId, Integer rate) {
        Book book = bookRepository.findBookById(bookId);

        BookRate bookRate = new BookRate();
        bookRate.setBook(book);
        bookRate.setRate(rate);

        bookRateRepository.save(bookRate);
    }

    public Integer getRateByBookSlug(String slug) {
        return bookRateRepository.findAvgByBook(bookRepository.findBookBySlug(slug));
    }

    public List<Integer> getRates(String slug) {
        List<Integer> rates = new ArrayList<>();
        Book book = bookRepository.findBookBySlug(slug);

        for (int i = 0; i < 5; ++i) {
            rates.add(bookRateRepository.countByBookAndRate(book, i + 1));
        }

        rates.add(bookRateRepository.countByBook(book));

        return rates;
    }

    public Integer getCountByBookAndRate(Integer bookId, Integer rate) {
        return bookRateRepository.countByBookAndRate(bookRepository.findBookById(bookId), rate);
    }
}
