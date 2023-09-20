package com.github.gypsyjr777.service;

import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.security.model.BookstoreUser;
import com.github.gypsyjr777.security.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PaymentService {
    private final RegistrationService registrationService;

    @Autowired
    public PaymentService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void pay(List<Book> books) throws NoSuchAlgorithmException {
        double paymentSumTotal = books.stream().mapToDouble(Book::discountPrice).sum();
        BookstoreUser user = (BookstoreUser) registrationService.getCurrentUser();
        user.decBalance(paymentSumTotal);
        registrationService.updateBalanceUser(user);
    }
}
