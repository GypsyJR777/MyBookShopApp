package com.github.GypsyJR777.MyBookShopApp.entity.book;

import com.github.GypsyJR777.MyBookShopApp.entity.book.Book;

import java.util.List;

public class BooksCount {
    private int count;
    private List<Book> books;

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
