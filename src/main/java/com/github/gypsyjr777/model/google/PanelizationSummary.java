package com.github.gypsyjr777.model.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PanelizationSummary {
    @JsonProperty("containsEpubBubbles")
    boolean containsEpubBubbles;

    @JsonProperty("containsImageBubbles")
    boolean containsImageBubbles;
}
