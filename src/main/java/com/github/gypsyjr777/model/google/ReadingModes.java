package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadingModes {
    @JsonProperty("text")
    boolean text;

    @JsonProperty("image")
    boolean image;
}
