package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.security.model.BookstoreUser;
import com.github.gypsyjr777.security.model.BookstoreUserDetails;
import com.github.gypsyjr777.security.repository.BookstoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookstoreUserDetailsService implements UserDetailsService {
    private final BookstoreUserRepository bookstoreUserRepository;

    @Autowired
    public BookstoreUserDetailsService(BookstoreUserRepository bookstoreUserRepository) {
        this.bookstoreUserRepository = bookstoreUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        BookstoreUser bookstoreUser;

        if (s.contains("@")){
            bookstoreUser = bookstoreUserRepository.findBookstoreUserByEmail(s);
        } else {
            bookstoreUser = bookstoreUserRepository.findBookstoreUserByName(s);
        }

        if (bookstoreUser != null) {
            return new BookstoreUserDetails(bookstoreUser);
        } else {
            throw new UsernameNotFoundException("user not found doh!");
        }
    }

    public void saveUser(BookstoreUser user) {
        bookstoreUserRepository.save(user);
    }
}
