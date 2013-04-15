package com.leetchi.api.client.model;


import org.codehaus.jackson.annotate.JsonProperty;

public class LeetchiError {

    @JsonProperty("TechnicalDescription")
    private String technicalDescription;

    public String getTechnicalDescription() {
        return technicalDescription;
    }
}
