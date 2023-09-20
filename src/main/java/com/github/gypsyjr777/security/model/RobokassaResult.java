package com.github.gypsyjr777.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RobokassaResult {
    private String shop;
    private String opKey;
    private int invId;
    private String paymentMethod;
    private double incSum;
    private String state;
}
