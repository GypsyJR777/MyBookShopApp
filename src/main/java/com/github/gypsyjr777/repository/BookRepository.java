package com.github.gypsyjr777.repository;

import com.github.gypsyjr777.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByAuthorFirstNameContaining(String authorsFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books)", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    Page<Book> findBookByPubDateBetweenOrderByPubDateDesc(LocalDate dateFrom, LocalDate dateTo, Pageable nextPage);

    @Query(value = "SELECT * FROM books ORDER BY purchase_amount + 0.7 * in_cart_amount + 0.4 * postponed_amount DESC",
            nativeQuery = true)
    Page<Book> findBookByPopularity(Pageable nextPage);

    @Query(
            value = "SELECT * FROM books b WHERE b.id IN (select bt.book_id from book2tag bt where bt.tag_id = ?1)",
            nativeQuery = true
    )
    Page<Book> findBookByIdInTag(Integer tagId, Pageable nextPage);

    @Query(
            value = "SELECT * FROM books b WHERE b.id IN (select bt.book_id from book2genre bt where bt.genre_id = ?1)",
            nativeQuery = true
    )
    Page<Book> findBookByIdInGenre(Integer genreId, Pageable nextPage);

    Page<Book> findBookByAuthorId(Integer author_id, Pageable nextPage);

    Book findBookBySlug(String slug);
}
