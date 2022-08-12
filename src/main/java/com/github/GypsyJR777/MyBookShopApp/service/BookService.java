package com.github.GypsyJR777.MyBookShopApp.service;

import com.github.GypsyJR777.MyBookShopApp.entity.Author;
import com.github.GypsyJR777.MyBookShopApp.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        List<Book> books = jdbcTemplate.query(
                "SELECT b.*, a.firstName, a.lastName FROM BOOKS AS b, AUTHORS AS a WHERE b.AUTHORID = a.ID",
                (ResultSet rs, int rowNum) -> {
                    Author author = new Author();
                    Book book = new Book();

                    author.setId(rs.getInt("authorId"));
                    author.setFirstName(rs.getString("firstName"));
                    author.setLastName(rs.getString("lastName"));

                    book.setId(rs.getInt("id"));
//                    book.setAuthor(author);
                    book.setTitle(rs.getString("title"));
                    book.setPriceOld(rs.getString("priceOld"));
                    book.setPrice(rs.getString("price"));

                    return book;
                });

        return new ArrayList<>(books);
    }
}
