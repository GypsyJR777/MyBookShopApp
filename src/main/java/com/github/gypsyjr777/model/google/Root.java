package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Root {
    @JsonProperty("kind")
    String kind;

    @JsonProperty("totalItems")
    int totalItems;

    @JsonProperty("items")
    ArrayList<Item> items;
}
