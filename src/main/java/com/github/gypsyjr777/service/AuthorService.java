package com.github.gypsyjr777.service;

import com.github.gypsyjr777.entity.author.Author;
import com.github.gypsyjr777.entity.author.FirstLetterAuthor;
import com.github.gypsyjr777.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                Collectors.groupingBy((Author a) -> a.getName().split(" ")[1].substring(0, 1))
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
