package com.qa.automation.services.impls;

import com.qa.automation.annotation.LazyAutowired;
import com.qa.automation.apiServices.RestService;
import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.constants.RestEndPoints;
import com.qa.automation.services.SignInService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Profile(ApplicationConstants.API)
public class SignInApiServiceImpl implements SignInService {

    @Value("${api.url}")
    private String baseUrl;

    @LazyAutowired
    private RestService restService;

    private RequestSpecification requestSpecification = null;

    private RequestSpecification getRequestSpecification() {
        if (requestSpecification == null) {
            RequestSpecBuilder builder = new RequestSpecBuilder();
            builder.setBaseUri(baseUrl);
            builder.addHeader("Content-type", "application/json");
            builder.addHeader("tenant", "695a1486-80e7-4ee6-bc55-f4911944ef2a");
            requestSpecification = builder.build();
        }
        return requestSpecification;
    }

    @Override
    public <T> T signIn(Map<String, String> userInfo) {

        Response response = restService.doHttpPostRequest(getRequestSpecification(), RestEndPoints.USER_SIGNIN, userInfo);
        return (T) response;

    }

    @Override
    public boolean isSignInSuccessful(String name) {
        return false;
    }

    @Override
    public boolean isSignInErrorMessageDisplayed() {
        return false;
    }
}
