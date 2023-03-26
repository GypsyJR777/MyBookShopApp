package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageLinks {
    @JsonProperty("smallThumbnail")
    String smallThumbnail;

    @JsonProperty("thumbnail")
    String thumbnail;
}
