package com.iqoption.swapi.service.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.when;

public class PlanetSteps {

    @Step("Request a planet with id={id}")
    public Response getById(int id) {
        return when().get("/planets/" + id);
    }
}
