package com.github.gypsyjr777.entity.book.links;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book2tag")
@Getter
@Setter
public class Book2Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
