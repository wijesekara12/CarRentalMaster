package com.b127.rental.modal;

public class ErrorModel {

    private String error;
    private String description;

    public ErrorModel(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "error='" + error + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
