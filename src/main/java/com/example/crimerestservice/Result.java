package com.example.crimerestservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private String longitude;
    private String latitude;
    private String postcode;

    public Result() {

    }

    public String getPostcode() {
        return postcode;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
