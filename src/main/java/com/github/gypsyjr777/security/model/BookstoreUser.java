package com.github.gypsyjr777.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class BookstoreUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @Column(columnDefinition = "DOUBLE NOT NULL DEFAULT 0")
    private double balance = 0;

    public void addBalance(double sum) {
        balance += sum;
    }

    public void decBalance(double sum) {
        balance -= sum;
    }
}
