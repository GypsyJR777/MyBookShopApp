package com.github.GypsyJR777.MyBookShopApp.repository;

import com.github.GypsyJR777.MyBookShopApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
