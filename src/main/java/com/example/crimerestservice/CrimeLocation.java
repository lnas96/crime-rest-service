package com.example.crimerestservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrimeLocation {

    private String category;
    private Result location;
    private String context;
    private OutcomeStatus outcome_status;
    private String month;

    public CrimeLocation() {

    }

    public String getCategory() {
        return category;
    }

    public Result getLocation() {
        return location;
    }

    public String getPostcode() {
        return location.getPostcode();
    }

    public String getContext() {
        return context;
    }

    public OutcomeStatus getOutcome_status() {
        return outcome_status;
    }

    public String getMonth() {
        return month;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(Result location) {
        this.location = location;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setOutcome_status(OutcomeStatus outcome_status) {
        this.outcome_status = outcome_status;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
