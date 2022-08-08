package com.github.GypsyJR777.MyBookShopApp.service;

import com.github.GypsyJR777.MyBookShopApp.entity.Author;
import com.github.GypsyJR777.MyBookShopApp.entity.FirstLetterAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Author> getAuthorsData() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int row) -> {
            Author author = new Author();

            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("firstName"));
            author.setLastName(rs.getString("lastName"));

            return author;
        });

        return new ArrayList<>(authors);
    }

    public List<FirstLetterAuthor> getMapAuthorsAndFirstLetters() {
        List<Author> authors = getAuthorsData();
        Map<String, List<Author>> fLettersAuthors = authors.stream().collect(
                Collectors.groupingBy((Author a) -> a.getLastName().substring(0, 1))
        );

        List<FirstLetterAuthor> firstLetterAuthors = new ArrayList<>();

        fLettersAuthors.forEach((letter, authorsList) -> {
            FirstLetterAuthor f = new FirstLetterAuthor();

            f.setAuthors(authorsList);
            f.setFirstLetter(letter);

            firstLetterAuthors.add(f);
        });

        return firstLetterAuthors;
    }
}
