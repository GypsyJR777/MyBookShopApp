package com.github.gypsyjr777.entity.book.rate;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rate")
@Getter
@Setter
public class BookRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private Integer rate;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private UserEntity user;
}
