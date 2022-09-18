package com.github.gypsyjr777.entity.book.links;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.genre.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book2genre")
@Getter
@Setter
public class Book2GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genre;
}
