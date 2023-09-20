package com.github.gypsyjr777.security.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PaymentPayload {
    private Integer sum;
    private LocalDateTime time;
}
