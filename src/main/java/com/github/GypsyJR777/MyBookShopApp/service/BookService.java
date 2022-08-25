package com.github.GypsyJR777.MyBookShopApp.service;

import com.github.GypsyJR777.MyBookShopApp.entity.Author;
import com.github.GypsyJR777.MyBookShopApp.entity.Book;
import com.github.GypsyJR777.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }
}
