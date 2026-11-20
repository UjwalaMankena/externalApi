package com.capitecassessment.externalApi.model;

import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timestamp;
    private  String errorMessage;
    private  String description;

    public ErrorResponse(LocalDateTime now, String message, String description) {
        this.timestamp = now;
        this.errorMessage = message;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
