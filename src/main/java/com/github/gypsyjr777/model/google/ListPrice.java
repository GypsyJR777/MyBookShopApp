package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListPrice {
    @JsonProperty("amount")
    double amount;

    @JsonProperty("currencyCode")
    String currencyCode;

    @JsonProperty("amountInMicros")
    long amountInMicros;
}
