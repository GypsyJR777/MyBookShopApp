package com.github.gypsyjr777.repository;

import com.github.gypsyjr777.entity.book.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Slf4j
class BookRepositoryTests {
    private final BookRepository bookRepository;

    @Autowired
    public BookRepositoryTests(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void findBooksByAuthorFirstNameContaining() {
        String token = "Carly";
        List<Book> bookListByAuthorFirstname = bookRepository.findBooksByAuthorFirstNameContaining(token);

        assertNotNull(bookListByAuthorFirstname);
        assertFalse(bookListByAuthorFirstname.isEmpty());

        bookListByAuthorFirstname.forEach(book -> {
            log.info(book.toString());
            assertThat(book.getAuthor().getFirstName()).contains(token);
        });
    }

    @Test
    void findBooksByTitleContaining() {
        String token = "Hearts";

        List<Book> bookListByTitle = bookRepository.findBooksByTitleContaining(token);

        assertNotNull(bookListByTitle);
        assertFalse(bookListByTitle.isEmpty());

        bookListByTitle.forEach(book -> {
            log.info(book.toString());
            assertThat(book.getTitle()).contains(token);
        });
    }

    @Test
    void getBestsellers() {
        List<Book> bookListBestsellers = bookRepository.getBestsellers();

        assertNotNull(bookListBestsellers);
        assertFalse(bookListBestsellers.isEmpty());

        bookListBestsellers.forEach(book -> {
            log.info(book.toString());
            assertThat(book.getIsBestseller()).isEqualTo(1);
        });
    }
}