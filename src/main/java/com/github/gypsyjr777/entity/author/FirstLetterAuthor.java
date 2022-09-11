package com.github.gypsyjr777.entity.author;

import java.util.List;

public class FirstLetterAuthor {
    private String firstLetter;
    private List<Author> authors;

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
