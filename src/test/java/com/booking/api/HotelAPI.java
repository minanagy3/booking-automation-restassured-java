package com.booking.api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class HotelAPI extends BaseAPI {
    private static final String HOTEL_ENDPOINT = "/hotels";

    public Response getHotelById(String hotelId) {
        return getRequestSpec()
            .pathParam("hotelId", hotelId)
            .when()
            .get(HOTEL_ENDPOINT + "/{hotelId}")
            .then()
            .extract()
            .response();
    }

    public Response getHotelAvailability(String hotelId, String checkIn, String checkOut) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkIn", checkIn);
        params.put("checkOut", checkOut);
        params.put("adults", 2);
        params.put("rooms", 1);

        return getRequestSpec()
            .pathParam("hotelId", hotelId)
            .queryParams(params)
            .when()
            .get(HOTEL_ENDPOINT + "/{hotelId}/availability")
            .then()
            .extract()
            .response();
    }

    public Response getHotelReviews(String hotelId) {
        return getRequestSpec()
            .pathParam("hotelId", hotelId)
            .when()
            .get(HOTEL_ENDPOINT + "/{hotelId}/reviews")
            .then()
            .extract()
            .response();
    }

    public Response getHotelRooms(String hotelId, String checkIn, String checkOut) {
        Map<String, Object> params = new HashMap<>();
        params.put("checkIn", checkIn);
        params.put("checkOut", checkOut);
        params.put("adults", 2);

        return getRequestSpec()
            .pathParam("hotelId", hotelId)
            .queryParams(params)
            .when()
            .get(HOTEL_ENDPOINT + "/{hotelId}/rooms")
            .then()
            .extract()
            .response();
    }
}

