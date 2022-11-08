package com.github.gypsyjr777.repository;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.book.rate.BookRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRateRepository extends JpaRepository<BookRate, Integer> {
    @Query("SELECT AVG(br.rate) AS Avg_Rate FROM BookRate as br where br.book = ?1")
    Integer findAvgByBook(Book book);

    Integer countByBookAndRate(Book book, Integer rate);

    Integer countByBook(Book book);
}
