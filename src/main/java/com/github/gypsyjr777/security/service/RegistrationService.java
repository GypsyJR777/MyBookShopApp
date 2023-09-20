package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.config.EmailConfig;
import com.github.gypsyjr777.security.errs.CanNotChangeDataException;
import com.github.gypsyjr777.security.errs.NoUserFoundException;
import com.github.gypsyjr777.security.jwt.JWTUtil;
import com.github.gypsyjr777.security.model.*;
import com.github.gypsyjr777.security.repository.BookstoreUserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationService {
    private final BookstoreUserRepository bookstoreUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BookstoreUserDetailsService bookstoreUserDetailsService;
    private final JWTUtil jwtUtil;
    private final JavaMailSender javaMailSender;
    private final EmailConfig emailConfig;


    public RegistrationService(BookstoreUserRepository bookstoreUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, BookstoreUserDetailsService bookstoreUserDetailsService, JWTUtil jwtUtil, JavaMailSender javaMailSender, EmailConfig emailConfig) {
        this.bookstoreUserRepository = bookstoreUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.javaMailSender = javaMailSender;
        this.emailConfig = emailConfig;
    }

    public void registerNewUser(RegistrationForm registrationForm) {
        if (bookstoreUserRepository.findBookstoreUserByEmail(registrationForm.getEmail()) == null) {
            BookstoreUser user = new BookstoreUser();

            user.setName(registrationForm.getName());
            user.setEmail(registrationForm.getEmail());
            user.setPhone(registrationForm.getPhone());
            user.setPassword(passwordEncoder.encode(registrationForm.getPass()));

            bookstoreUserRepository.save(user);
        } else {
            throw new NoUserFoundException("No user found");
        }
    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                payload.getContact(),
                                payload.getCode()
                        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        ContactConfirmationResponse response = new ContactConfirmationResponse();

        response.setResult("true");
        return response;
    }

    public ContactConfirmationResponse jwtLogin(ContactConfirmationPayload payload) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),
                payload.getCode()));
        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) bookstoreUserDetailsService.loadUserByUsername(payload.getContact());

        String jwtToken = jwtUtil.generateToken(userDetails);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);

        return response;
    }

    public Object getCurrentUser() {
        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getBookstoreUser();
    }

    public ContactConfirmationResponse jwtLoginByPhoneNumber(ContactConfirmationPayload payload) {
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setPhone(payload.getContact());
        registrationForm.setPass(payload.getCode());
        registerNewUser(registrationForm);
        UserDetails userDetails = bookstoreUserDetailsService.loadUserByUsername(payload.getContact());
        String jwtToken = jwtUtil.generateToken(userDetails);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult(jwtToken);
        return response;
    }

    public void changeDataUser(ProfileDetail userChange) {
        if (userChange.getPassword() != null && !userChange.getPassword().equals(userChange.getPasswordReply())) {
            throw new CanNotChangeDataException("Пароли не совпадают");
        }

        BookstoreUserDetails userDetails =
                (BookstoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BookstoreUser user = userDetails.getBookstoreUser();
        user.setEmail(userChange.getMail());
        user.setPhone(userChange.getPhone());
        user.setName(userChange.getName());
        if (!userChange.getPassword().isBlank()){
            user.setPassword(passwordEncoder.encode(userChange.getPassword()));
        }
        bookstoreUserRepository.save(user);

        boolean isEdited = false;
        if (!userChange.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(userChange.getPassword()));
            isEdited = true;
        }
        if (userChange.getName() != null && !user.getName().equals(userChange.getName())) {
            user.setName(userChange.getName());
            isEdited = true;
        }
        if (userChange.getMail() != null && !user.getEmail().equals(userChange.getMail())) {
            user.setEmail(userChange.getMail());
            isEdited = true;
        }
        if (userChange.getPhone() != null && !user.getPhone().equals(userChange.getPhone())) {
            user.setPhone(userChange.getPhone());
            isEdited = true;
        }

        if (isEdited) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getName());
            claims.put("mail", user.getEmail());
            claims.put("phone", user.getPhone());
            claims.put("pass", user.getPassword());
            String jwtToken = jwtUtil.generateToken(new BookstoreUserDetails(user), claims);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailConfig.getEmail());
            message.setTo(userChange.getMail());
            message.setSubject("Bookstore change profile conformation!");
            message.setText(
                    "Verification link is: http://localhost:8085/conformationCheck?token=" + jwtToken);
            javaMailSender.send(message);
        }
    }

    public void changeDataUser(String token) {
        BookstoreUser user =
                ((BookstoreUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getBookstoreUser();

        user.setName(jwtUtil.extractAllClaims(token).get("username", String.class));
        user.setEmail(jwtUtil.extractAllClaims(token).get("mail", String.class));
        user.setPhone(jwtUtil.extractAllClaims(token).get("phone", String.class));
        user.setPassword(jwtUtil.extractAllClaims(token).get("pass", String.class));

        bookstoreUserRepository.save(user);
    }

    public void updateBalanceUser(BookstoreUser user) {
        bookstoreUserRepository.save(user);
    }
}
