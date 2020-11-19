package com.example.crimerestservice.controller;

import com.example.crimerestservice.model.CrimeCategories;
import com.example.crimerestservice.model.CrimeLocation;
import com.example.crimerestservice.model.Postcode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class CrimeController {

    @Autowired
    private RestTemplate restTemplate;

    private static String urlCrimeCategories = "https://data.police.uk/api/crime-categories";
    private static String urlCrimesAtLocation = "https://data.police.uk/api/crimes-at-location";
    private static String urlPostcode = "https://api.postcodes.io/postcodes/";

    @RequestMapping(value = "/crime/categories", produces = {MediaType.APPLICATION_JSON_VALUE })
    public String getCrimeCategories() throws JSONException {

        ResponseEntity<CrimeCategories[]> responseEntity;

        //gets the response from the API - returns 404 status if no data found
        try {
            responseEntity = restTemplate.getForEntity(urlCrimeCategories, CrimeCategories[].class);
        } catch (HttpServerErrorException.InternalServerError e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "404 no data found"
            );
        }

        List<CrimeCategories> crimeList = Arrays.asList(responseEntity.getBody());

        //creates a list of just the categories
        List<String> categoryList = crimeList.stream().map(entity -> entity.getName()).collect(Collectors.toList());

        //formats the category list into JSON with "Categories" as the list parent
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray(categoryList);
        jsonObject.put("categories", jsonArray);

        return jsonObject.toString();
    }

    @RequestMapping(value = "/crimes")
    public ResponseEntity<CrimeLocation[]> getCrimesAtLocation(@RequestParam(value = "postcode") String pPostcode, @RequestParam(value = "date") String pDate) {

        String regexPostcode = "^([A-PR-UWYZ](([0-9](([0-9]|[A-HJKSTUW])?)?)|([A-HK-Y][0-9]([0-9]|[ABEHMNPRVWXY])?)) ?[0-9][ABD-HJLNP-UW-Z]{2})$";
        String regexDate = "^\\d{4}\\-(0[1-9]|1[012])$";

        Pattern patternPostcode = Pattern.compile(regexPostcode);
        Matcher matcherPostcode = patternPostcode.matcher(pPostcode.toUpperCase());
        Pattern patternDate = Pattern.compile(regexDate);
        Matcher matcherDate= patternDate.matcher(pDate);

        //checks if both the postcode and date match their corresponding regular expression
        if (!matcherPostcode.matches() || !matcherDate.matches()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //gets the longitude and latitude for the given postcode from the API
        Postcode postcode = restTemplate.getForObject(urlPostcode + pPostcode, Postcode.class);

        //formats the police url to contain postcode data
        String urlComplete =
                urlCrimesAtLocation
                + "?date="
                + pDate
                + "&lat="
                + postcode.getLatitude()
                + "&lng="
                + postcode.getLongitude();

        //gets the response from the API - returns 404 status if no data found
        try {
            return restTemplate.getForEntity(urlComplete, CrimeLocation[].class);
        } catch (HttpServerErrorException.InternalServerError e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}