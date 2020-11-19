package com.example.crimerestservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutcomeStatus {
    private String category;
    private String date;

    public OutcomeStatus() {

    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}
