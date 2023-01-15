package com.github.gypsyjr777.repository;

import com.github.gypsyjr777.entity.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewLikeRepository extends JpaRepository<BookReviewLikeEntity, Integer> {
}
