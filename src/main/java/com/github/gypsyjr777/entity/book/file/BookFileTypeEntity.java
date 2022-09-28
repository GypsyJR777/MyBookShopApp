package com.github.gypsyjr777.entity.book.file;

import com.github.gypsyjr777.entity.enums.FileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book_file_type")
@Getter
@Setter
public class BookFileTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private FileType name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
