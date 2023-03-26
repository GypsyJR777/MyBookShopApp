package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class VolumeInfo {
    @JsonProperty("title")
    String title;

    @JsonProperty("authors")
    ArrayList<String> authors;

    @JsonProperty("publisher")
    String publisher;

    @JsonProperty("publishedDate")
    String publishedDate;

    @JsonProperty("description")
    String description;

    @JsonProperty("industryIdentifiers")
    ArrayList<IndustryIdentifier> industryIdentifiers;

    @JsonProperty("readingModes")
    ReadingModes readingModes;

    @JsonProperty("pageCount")
    int pageCount;

    @JsonProperty("printType")
    String printType;

    @JsonProperty("categories")
    ArrayList<String> categories;

    @JsonProperty("maturityRating")
    String maturityRating;

    @JsonProperty("allowAnonLogging")
    boolean allowAnonLogging;

    @JsonProperty("contentVersion")
    String contentVersion;

    @JsonProperty("panelizationSummary")
    PanelizationSummary panelizationSummary;

    @JsonProperty("imageLinks")ImageLinks imageLinks;

    @JsonProperty("language")
    String language;

    @JsonProperty("previewLink")
    String previewLink;

    @JsonProperty("infoLink")
    String infoLink;

    @JsonProperty("canonicalVolumeLink")

    String canonicalVolumeLink;

    @JsonProperty("averageRating")
    int averageRating;

    @JsonProperty("ratingsCount")
    int ratingsCount;
}
