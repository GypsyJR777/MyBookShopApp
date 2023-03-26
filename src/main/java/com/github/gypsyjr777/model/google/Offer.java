package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {
    @JsonProperty("finskyOfferType")
    int finskyOfferType;

    @JsonProperty("listPrice")
    ListPrice listPrice;

    @JsonProperty("retailPrice")
    RetailPrice retailPrice;
}
