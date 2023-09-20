package com.github.gypsyjr777.entity.payments;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.user.UserEntity;
import com.github.gypsyjr777.security.model.BookstoreUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "balance_transaction")
@Getter
@Setter
public class BalanceTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
//    private UserEntity user;
    private BookstoreUser user;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "DOUBLE PRECISION NOT NULL DEFAULT 0")
    private double value;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String description;
}
