package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.security.model.BookstoreUser;
import com.github.gypsyjr777.security.model.ContactConfirmationPayload;
import com.github.gypsyjr777.security.model.ContactConfirmationResponse;
import com.github.gypsyjr777.security.model.RegistrationForm;
import com.github.gypsyjr777.security.repository.BookstoreUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistrationServiceTest {
    private final RegistrationService service;
    private final BookstoreUserRepository repository;

    private static final RegistrationForm form = new RegistrationForm();

    @Autowired
    RegistrationServiceTest(RegistrationService service, BookstoreUserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @BeforeAll
    public static void init() {
        form.setEmail("123456@mail.ru");
        form.setName("123456");
        form.setPhone("+79999999999");
        form.setPass("123456");
    }

    @Test
    @Order(1)
    void registerNewUser() {

        service.registerNewUser(form);

        BookstoreUser user = repository.findBookstoreUserByEmail(form.getEmail());
        assertEquals(form.getName(), user.getName());
    }

    @Test
    @Order(2)
    void jwtLogin() {
        ContactConfirmationPayload contact = new ContactConfirmationPayload();
        contact.setContact("123456@mail.ru");
        contact.setCode("123456");

        ContactConfirmationResponse response = service.jwtLogin(contact);

        assertNotNull(response.getResult());
        assertNotEquals("", response.getResult());

        repository.delete(repository.findBookstoreUserByEmail(form.getEmail()));
    }
}