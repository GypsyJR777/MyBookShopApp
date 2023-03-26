package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    @JsonProperty("kind")
    String kind;

    @JsonProperty("id")
    String id;

    @JsonProperty("etag")
    String etag;

    @JsonProperty("selfLink")
    String selfLink;

    @JsonProperty("volumeInfo")
    VolumeInfo volumeInfo;

    @JsonProperty("saleInfo")
    SaleInfo saleInfo;

    @JsonProperty("accessInfo")
    AccessInfo accessInfo;

    @JsonProperty("searchInfo")
    SearchInfo searchInfo;
}
