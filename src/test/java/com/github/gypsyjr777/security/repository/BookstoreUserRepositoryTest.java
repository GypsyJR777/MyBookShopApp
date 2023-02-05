package com.github.gypsyjr777.security.repository;

import com.github.gypsyjr777.security.model.BookstoreUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class BookstoreUserRepositoryTest {
    private final BookstoreUserRepository bookstoreUserRepository;

    @Autowired
    public BookstoreUserRepositoryTest(BookstoreUserRepository bookstoreUserRepository) {
        this.bookstoreUserRepository = bookstoreUserRepository;
    }

    @BeforeEach
    public void addNewUserTest() {
        BookstoreUser user = new BookstoreUser();
        user.setPassword("123456789");
        user.setEmail("123@mail.com");
        user.setName("123");
        user.setPhone("1234567889");

        assertNotNull(bookstoreUserRepository.save(user));
        bookstoreUserRepository.delete(user);
    }

    @Test
    public void checkNewUserTest() {
        BookstoreUser user = new BookstoreUser();

        user.setPassword("123456789");
        user.setEmail("123@mail.com");
        user.setName("123");
        user.setPhone("1234567889");
        bookstoreUserRepository.save(user);

        BookstoreUser userResult = bookstoreUserRepository.findBookstoreUserByEmail("123@mail.com");
        assertEquals(user.getName(), userResult.getName());

        bookstoreUserRepository.delete(user);
    }

    @Test
    public void authorizationTest() {

    }
}