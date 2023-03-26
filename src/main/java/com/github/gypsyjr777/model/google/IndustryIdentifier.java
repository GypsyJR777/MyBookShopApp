package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryIdentifier {
    @JsonProperty("type")
    String type;

    @JsonProperty("identifier")
    String identifier;
}
