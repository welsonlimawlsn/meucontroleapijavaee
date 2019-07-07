package com.meucontrole.api.util;

public class Error {

    private String devMessage;
    private String message;
    private int status;

    public Error(String devMessage, String message, int status) {
        this.devMessage = devMessage;
        this.message = message;
        this.status = status;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
