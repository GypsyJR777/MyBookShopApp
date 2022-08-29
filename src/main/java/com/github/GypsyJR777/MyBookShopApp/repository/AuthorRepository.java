package com.github.GypsyJR777.MyBookShopApp.repository;

import com.github.GypsyJR777.MyBookShopApp.entity.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
