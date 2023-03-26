package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pdf {

    @JsonProperty("isAvailable")
    boolean isAvailable;

    @JsonProperty("acsTokenLink")
    String acsTokenLink;
}
