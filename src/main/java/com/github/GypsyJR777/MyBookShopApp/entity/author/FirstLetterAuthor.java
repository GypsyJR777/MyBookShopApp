package com.github.GypsyJR777.MyBookShopApp.entity.author;

import com.github.GypsyJR777.MyBookShopApp.entity.author.Author;

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
