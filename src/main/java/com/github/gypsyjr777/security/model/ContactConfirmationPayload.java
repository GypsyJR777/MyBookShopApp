package com.github.gypsyjr777.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactConfirmationPayload {

    private String contact;
    private String code;
}
