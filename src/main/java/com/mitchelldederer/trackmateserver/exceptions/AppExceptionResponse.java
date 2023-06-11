package com.mitchelldederer.trackmateserver.exceptions;

public class AppExceptionResponse {
    private String message;

    public AppExceptionResponse() {
    }

    public AppExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
