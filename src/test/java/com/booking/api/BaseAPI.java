package com.booking.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPI {
    protected static final String BASE_URI = "https://www.booking.com";
    protected static final String API_BASE_PATH = "/api";
    
    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    public BaseAPI() {
        // Base request specification
        requestSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setBasePath(API_BASE_PATH)
            .setContentType(ContentType.JSON)
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", "Mozilla/5.0")
            .build();

        // Base response specification
        responseSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .build();

        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    protected RequestSpecification getRequestSpec() {
        return RestAssured.given().spec(requestSpec);
    }
}

