package com.qa.automation.apiServices;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

/**
 * Utility class for http actions
 */
@Service
public class RestService {

    /**
     * Http method for POST Request
     *
     * @param requestSpecification
     * @param path
     * @return
     */
    public Response doHttpPostRequest(RequestSpecification requestSpecification, String path, Object object) {
        return given(requestSpecification)
                .body(object)
                .when().log().all().post(path)
                .then().log().all()
                .extract().response();

    }

}
