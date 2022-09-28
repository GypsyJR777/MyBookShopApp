package com.github.gypsyjr777.repository;

import com.github.gypsyjr777.entity.book.file.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFileRepository extends JpaRepository<BookFile, Integer> {
    BookFile findBookFileByHash(String hash);
}
