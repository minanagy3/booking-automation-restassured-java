package com.booking.api;

import io.restassured.response.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SearchAPI extends BaseAPI {
    private static final String SEARCH_ENDPOINT = "/search";

    public Response searchHotels(String location, LocalDate checkIn, LocalDate checkOut) {
        Map<String, Object> params = new HashMap<>();
        params.put("location", location);
        params.put("checkIn", formatDate(checkIn));
        params.put("checkOut", formatDate(checkOut));
        params.put("adults", 2);
        params.put("rooms", 1);

        return getRequestSpec()
            .queryParams(params)
            .when()
            .get(SEARCH_ENDPOINT)
            .then()
            .extract()
            .response();
    }

    public Response searchHotelsWithFilters(String location, LocalDate checkIn, LocalDate checkOut, 
                                           Map<String, Object> filters) {
        Map<String, Object> params = new HashMap<>();
        params.put("location", location);
        params.put("checkIn", formatDate(checkIn));
        params.put("checkOut", formatDate(checkOut));
        params.put("adults", 2);
        params.put("rooms", 1);
        params.putAll(filters);

        return getRequestSpec()
            .queryParams(params)
            .when()
            .get(SEARCH_ENDPOINT)
            .then()
            .extract()
            .response();
    }

    public Response getHotelDetails(String hotelId) {
        return getRequestSpec()
            .pathParam("hotelId", hotelId)
            .when()
            .get("/hotels/{hotelId}")
            .then()
            .extract()
            .response();
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}

