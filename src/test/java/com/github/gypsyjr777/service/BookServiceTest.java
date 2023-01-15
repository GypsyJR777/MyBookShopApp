package com.github.gypsyjr777.service;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.rate.BookRate;
import com.github.gypsyjr777.entity.book.review.BookReviewLikeEntity;
import com.github.gypsyjr777.repository.BookRateRepository;
import com.github.gypsyjr777.repository.BookRepository;
import com.github.gypsyjr777.repository.BookReviewLikeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookRateRepository rateRepository;
    @Autowired
    private BookReviewLikeRepository bookReviewLikeRepository;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void init() {

        book1 = new Book();
        book1.setTitle("Book 1");
        book1.setPurchaseAmount(13L);
        book1.setInCartAmount(50L);
        book1.setPostponedAmount(60L);
        bookRepository.save(book1);

        book2 = new Book();
        book2.setTitle("Book 2");
        book2.setPurchaseAmount(12L);
        book2.setInCartAmount(40L);
        book2.setPostponedAmount(50L);
        bookRepository.save(book2);
    }

    @AfterEach
    public void delete() {
        bookRepository.delete(book1);
        bookRepository.delete(book2);
    }

    @Test
    void getPageOfRecommendedBooks() {
        BookRate rate1 = new BookRate();
        rate1.setBook(book1);
        rate1.setRate(4);
        rateRepository.save(rate1);;

        BookRate rate2 = new BookRate();
        rate2.setBook(book2);
        rate2.setRate(3);
        rateRepository.save(rate2);;

        Pageable nextPage = PageRequest.of(0, 5);
        List<Book> books = bookRepository.findAll(nextPage).getContent();

        assertEquals(book1.getTitle(), books.get(0).getTitle());
        assertEquals(book2.getTitle(), books.get(1).getTitle());


        rateRepository.delete(rate1);
        rateRepository.delete(rate2);
    }

    @Test
    void getPageOfPopularBooks() {
        Pageable nextPage = PageRequest.of(0, 5);
        List<Book> books = bookRepository.findBookByPopularity(nextPage).getContent();

        assertEquals(book1.getTitle(), books.get(0).getTitle());
        assertEquals(book2.getTitle(), books.get(1).getTitle());
    }

    //TODO Во время реализации оценивания дописать тест
    @Test
    void getReviews() {
        BookReviewLikeEntity bookReviewLike = new BookReviewLikeEntity();
        bookReviewLike.setValue((short) 5);
        bookReviewLike.setTime(LocalDateTime.now());
        bookReviewLikeRepository.save(bookReviewLike);

        List<BookReviewLikeEntity> bookReviewLikeEntities = bookReviewLikeRepository.findAll();

        assertEquals(bookReviewLike.getId(), bookReviewLikeEntities.get(0).getId());

        bookReviewLikeRepository.delete(bookReviewLike);
    }
}