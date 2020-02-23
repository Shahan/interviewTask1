package com.iqoption.swapi.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class StarWarsApiStarshipTests {

    @BeforeClass(description = "Set base API url and path", alwaysRun = true)
    public void beforeClass() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("test");

        //I know that's bad for parallel execution, but let's forgive it while we have only 3 tests
        RestAssured.baseURI = resourceBundle.getString("baseApiUrl");
        RestAssured.basePath = resourceBundle.getString("baseApiPath");

        RestAssured.filters(new AllureRestAssured());

        Allure.addAttachment("Base API URL", RestAssured.baseURI);
        Allure.addAttachment("Base API PATH", RestAssured.basePath);
    }

    @Test(description = "Verifies all search results of 'Starfighter' to have a field 'starship_class' containing 'Starfighter'substring")
    public void testSearchResultsOfStarfighterStarshipSearchRequest() {
        when()
                .get("/starships/?search=Starfighter")

        .then().assertThat()
                .contentType("application/json")
                .statusCode(200)

                .body("results*.starship_class", everyItem(containsStringIgnoringCase("Starfighter")))
        ;
    }

    @Test(groups = "smoke", description = "Smoke test verifies all fields of starship with id=39 except pilots and films")
    public void verifyThirtyNinethStarshipFields() {
        when()
                .get("/starships/39")

        .then().assertThat()
                .contentType("application/json")
                .statusCode(200)

                .body("name", equalTo("Naboo fighter"))
                .body("model", equalTo("N-1 starfighter"))
                .body("manufacturer", equalTo("Theed Palace Space Vessel Engineering Corps"))
                .body("cost_in_credits", equalTo("200000"))
                .body("length", equalTo("11"))
                .body("max_atmosphering_speed", equalTo("1100"))
                .body("crew", equalTo("1"))
                .body("passengers", equalTo("0"))
                .body("cargo_capacity", equalTo("65"))
                .body("consumables", equalTo("7 days"))
                .body("hyperdrive_rating", equalTo("1.0"))
                .body("MGLT", equalTo("unknown"))
                .body("starship_class", equalTo("Starfighter"))
                .body("created", equalTo("2014-12-19T17:39:17.582000Z"))
                .body("edited", equalTo("2014-12-22T17:35:45.079452Z"))
                .body("url", equalTo("https://swapi.co/api/starships/39/"))
        ;
    }

    @Test(groups = "smoke", description = "Smoke test verifies that planet with id=0 does not exist")
    public void smokeTestZeroIdPlanetNotExists() {
        when()
                .get("/planets/0")

        .then().assertThat()
                .contentType("application/json")
                .statusCode(404)

                .body("detail", equalTo("Not found"));
    }
}
