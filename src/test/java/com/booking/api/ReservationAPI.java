package com.booking.api;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class ReservationAPI extends BaseAPI {
    private static final String RESERVATION_ENDPOINT = "/reservations";

    public Response createReservation(Map<String, Object> reservationData) {
        return getRequestSpec()
            .body(reservationData)
            .when()
            .post(RESERVATION_ENDPOINT)
            .then()
            .extract()
            .response();
    }

    public Response getReservation(String reservationId) {
        return getRequestSpec()
            .pathParam("reservationId", reservationId)
            .when()
            .get(RESERVATION_ENDPOINT + "/{reservationId}")
            .then()
            .extract()
            .response();
    }

    public Response cancelReservation(String reservationId) {
        return getRequestSpec()
            .pathParam("reservationId", reservationId)
            .when()
            .delete(RESERVATION_ENDPOINT + "/{reservationId}")
            .then()
            .extract()
            .response();
    }

    public Response updateReservation(String reservationId, Map<String, Object> updateData) {
        return getRequestSpec()
            .pathParam("reservationId", reservationId)
            .body(updateData)
            .when()
            .put(RESERVATION_ENDPOINT + "/{reservationId}")
            .then()
            .extract()
            .response();
    }
}

