package com.iqoption.swapi.tests;

import com.google.gson.Gson;
import com.iqoption.swapi.domain.Starship;
import com.iqoption.swapi.service.steps.AssertionApiSteps;
import com.iqoption.swapi.service.steps.PlanetSteps;
import com.iqoption.swapi.service.steps.StarshipSteps;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class StarWarsApiTests {

    //Explicit initialization because we don't have DI.
    PlanetSteps planet = new PlanetSteps();
    StarshipSteps starship = new StarshipSteps();
    AssertionApiSteps assertion = new AssertionApiSteps();

    @BeforeClass(description = "Set base API url", alwaysRun = true)
    public void beforeClass() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("test");

        //I know that's bad for parallel execution, but let's forgive it while we have only 3 tests
        RestAssured.baseURI = resourceBundle.getString("baseApiUrl");

        RestAssured.filters(new AllureRestAssured());
        Allure.addAttachment("Base API URL", RestAssured.baseURI);
    }

    @Test(description = "Verifies all search results of 'Starfighter' to have a field 'starship_class' containing 'Starfighter' substring")
    public void testSearchResultsOfStarfighterStarshipSearchRequest() {
        Response response = starship.getSearchResultBy("Starfighter");

        assertion.responseHasStatusCode(response, 200);
        assertion.everyResponseListItemContainsSubstringInAField(response, "results*.starship_class", "Starfighter", true);
    }

    @Test(groups = "smoke", description = "Smoke test verifies all fields of starship with id=39 except pilots and films")
    public void verifyThirtyNinethStarshipFields() {
        Starship actualStarship = starship.getStarshipByid(39);
        Starship expectedStarship = starship.loadFromTestResourceFile("data/starship_39_without_pilots_and_films.json");

        assertion.objectsFieldsValuesAreTheSame("Starship", actualStarship, expectedStarship);
    }

    @Test(groups = "smoke", description = "Smoke test verifies that planet with id=0 does not exist")
    public void smokeTestZeroIdPlanetNotExists() {
        Response response = planet.getById(0);
        assertion.responseHasStatusCodeAndDetailMessage(response, 404, "Not found");
    }
}
