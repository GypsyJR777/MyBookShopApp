package com.github.gypsyjr777.entity.book;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class BooksCount {
    private int count;
    private List<Book> books;

    public BooksCount() {
    }

    public BooksCount(List<Book> books) {
        this.count = books.size();
        this.books = books;
    }
}
