package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class SaleInfo {
    @JsonProperty("country")
    String country;

    @JsonProperty("saleability")
    String saleability;


    @JsonProperty("isEbook")
    boolean isEbook;

    @JsonProperty("listPrice")
    ListPrice listPrice;

    @JsonProperty("retailPrice")
    RetailPrice retailPrice;

    @JsonProperty("buyLink")
    String buyLink;

    @JsonProperty("offers")
    ArrayList<Offer> offers;
}
