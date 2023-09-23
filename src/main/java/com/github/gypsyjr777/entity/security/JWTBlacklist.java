package com.github.gypsyjr777.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jwt_blacklist")
@Getter
@Setter
public class JWTBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "closed_date")
    private Date date;
}
