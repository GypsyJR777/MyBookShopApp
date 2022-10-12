package com.github.gypsyjr777.repository;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.review.BookReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReviewEntity, Integer> {
    List<BookReviewEntity> findAllByBook(Book book);
}
