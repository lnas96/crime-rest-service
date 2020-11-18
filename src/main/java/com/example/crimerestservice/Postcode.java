package com.example.crimerestservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Postcode {

    private Result result;

    public Postcode() {

    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public String getLongitude() {
        return result.getLongitude();
    }

    public String getLatitude() {
        return result.getLatitude();
    }
}

