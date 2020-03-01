package com.iqoption.swapi.service.steps;

import com.google.gson.Gson;
import com.iqoption.swapi.domain.Starship;
import com.iqoption.swapi.service.utils.ResourceLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class StarshipSteps {

    private static final String BASE_PATH = "/starships";

    @Step("Request a starship with id={id}")
    public Response getResponseById(int id) {
        return defaultRequestSpecification().get("/" + id);
    }

    @Step("Request and deserialize starship with id={id}")
    public Starship getStarshipByid(int id) {
        return new Gson().fromJson(
                getResponseById(id).asString(),
                Starship.class
        );
    }

    @Step("Search for '{searchString}' starships")
    public Response getSearchResultBy(String searchString) {
        return defaultRequestSpecification().get("/?search=" + searchString);
    }

    @Step("Load JSON from {file} and deserialize to starship object")
    public Starship loadFromTestResourceFile(String file) {
        return new Gson().fromJson(ResourceLoader.readFileToString(file), Starship.class);
    }

    private RequestSpecification defaultRequestSpecification() {
        return given().basePath(BASE_PATH);
    }
}
