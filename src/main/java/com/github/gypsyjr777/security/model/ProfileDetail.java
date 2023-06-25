package com.github.gypsyjr777.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDetail {
    private String name;
    private String mail;
    private String phone;
    private String password;
    private String passwordReply;
}
