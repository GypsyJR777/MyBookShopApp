package com.github.gypsyjr777.entity.book;

import java.util.List;

public class BooksCount {
    private int count;
    private List<Book> books;

    public BooksCount() {
    }

    public BooksCount(List<Book> books) {
        this.count = books.size();
        this.books = books;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
