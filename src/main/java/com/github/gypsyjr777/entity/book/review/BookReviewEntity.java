package com.github.gypsyjr777.entity.book.review;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "book_review")
@Getter
@Setter
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(columnDefinition = "TIMESTAMP NOT NULL")
    private LocalDateTime time;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String text;

    public String getFormatDate() {
        return time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public String getLittleReview() {
        String[] littleReview = text.split(". ");
        if (text.length() < 600) {
            return text;
        }

        return text.substring(0, 600);
    }

    public String getOtherPartReview() {
        return text.substring(600);
    }

    public boolean isBigReview() {
        return text.length() >= 600;
    }
}
