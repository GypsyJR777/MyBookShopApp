package com.github.gypsyjr777.security.repository;

import com.github.gypsyjr777.security.model.BookstoreUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookstoreUserRepository extends JpaRepository<BookstoreUser, Integer> {
    BookstoreUser findBookstoreUserByEmail(String email);

    BookstoreUser findBookstoreUserByName(String name);
}
