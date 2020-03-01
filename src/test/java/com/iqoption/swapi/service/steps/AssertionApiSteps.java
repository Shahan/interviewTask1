package com.iqoption.swapi.service.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AssertionApiSteps {

    @Step("Assert that response has status code '{expectedStatusCode}' and detail message '{expectedDetailMessage}'")
    public void responseHasStatusCodeAndDetailMessage(Response response, int expectedStatusCode, String expectedDetailMessage) {
        response.then().assertThat()
                .contentType("application/json")
                .statusCode(expectedStatusCode)
                .body("detail", equalTo(expectedDetailMessage));
    }

    @Step("Assert that response has status code '{expectedStatusCode}'")
    public void responseHasStatusCode(Response response, int expectedStatusCode) {
        response.then().assertThat()
                .contentType("application/json")
                .statusCode(expectedStatusCode);
    }

    @Step("Assert that every item in response list contains '{expectedSubstring}' in field by path '{fieldGPath}'")
    public void everyResponseListItemContainsSubstringInAField(Response response, String fieldGPath, String expectedSubstring, boolean ignoreCase) {
        response.then().assertThat()
                .body(fieldGPath,
                        everyItem(ignoreCase ?
                                containsStringIgnoringCase(expectedSubstring) :
                                containsString(expectedSubstring)));
    }

    @Step("Assert that {objectName} objects are equal by field values")
    public <T> void objectsFieldsValuesAreTheSame(String objectName, T actual, T expected) {
        Allure.addAttachment("Actual " + objectName, actual == null ? "" : actual.toString());
        Allure.addAttachment("Expected " + objectName, expected == null ? "" : expected.toString());
        assertThat(actual, samePropertyValuesAs(expected));
    }
}
