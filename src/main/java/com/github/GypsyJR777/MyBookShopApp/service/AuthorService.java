package com.github.GypsyJR777.MyBookShopApp.service;

import com.github.GypsyJR777.MyBookShopApp.entity.Author;
import com.github.GypsyJR777.MyBookShopApp.entity.FirstLetterAuthor;
import com.github.GypsyJR777.MyBookShopApp.repository.AuthorRepository;
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
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthorsData() {
        return authorRepository.findAll();
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
