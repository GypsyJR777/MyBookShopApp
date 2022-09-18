package com.github.gypsyjr777.entity.book.file;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file_download")
@Getter
@Setter
public class FileDownloadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(columnDefinition = "INT NOT NULL DEFAULT 1")
    private int count;
}
