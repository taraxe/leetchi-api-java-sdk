package com.leetchi.api.client.model;


import org.codehaus.jackson.annotate.JsonProperty;

public class LeetchiError extends Error {

    @JsonProperty("ErrorCode")
    private String errorCode;
    @JsonProperty("TechnicalMessage")
    private String technicalMessage;
    @JsonProperty("UserMessage")
    private String userMessage;
    @JsonProperty("Type")
    private String type;

    public String getErrorCode() {
        return errorCode;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getType() {
        return type;
    }
}
