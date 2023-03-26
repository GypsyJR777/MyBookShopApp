package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessInfo {
    @JsonProperty("country")
    String country;

    @JsonProperty("viewability")
    String viewability;

    @JsonProperty("embeddable")
    boolean embeddable;

    @JsonProperty("publicDomain")
    boolean publicDomain;

    @JsonProperty("textToSpeechPermission")
    String textToSpeechPermission;

    @JsonProperty("epub")
    Epub epub;

    @JsonProperty("pdf")
    Pdf pdf;

    @JsonProperty("webReaderLink")
    String webReaderLink;

    @JsonProperty("accessViewStatus")
    String accessViewStatus;

    @JsonProperty("quoteSharingAllowed")
    boolean quoteSharingAllowed;
}

