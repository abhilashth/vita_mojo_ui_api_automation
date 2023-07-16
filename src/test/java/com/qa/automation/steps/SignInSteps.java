package com.qa.automation.steps;


import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.services.SignInService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

import java.util.Map;


public class SignInSteps<T> {

    @Autowired
    private SignInService signInService;

    Map<String, String> userInfo;
    private T signInInfo;

    @Value("${spring.profiles.active}")
    private String testsType;

    @Given("I want to signIn with details")
    public void signIn(DataTable userTable) {
        userInfo = userTable.asMaps(String.class, String.class).get(0);
        signInInfo = signInService.signIn(userInfo);
    }

    @Then("I verify if signIn is successful")
    public void verifyIfSignInIsSuccessful() {
        if (testsType.equals(ApplicationConstants.UI)) {
            boolean result = signInService.isSignInSuccessful(userInfo.get("name"));
            Assert.assertTrue(result, "Signup is not successful !!");
        } else {
            // Here to verify whether SignIn is successful or not, no need to make api call.
            // we can use response from initial call to validate response
            Response response = (Response) signInInfo;
            Assert.assertEquals(response.getStatusCode(), 201, "SignIn is not successful !!");
            String name = response.jsonPath().get("payload.user.profile.firstName");
            Assert.assertEquals(name, userInfo.get("name"), "SignIn is not successful !!");
        }
    }

    @Then("I verify that SignIn error message is displayed")
    public void verifySignInErrorMessage() {
        if (testsType.equals(ApplicationConstants.UI)) {
            boolean result = signInService.isSignInErrorMessageDisplayed();
            Assert.assertTrue(result, "Signup error message is not displayed!");
        } else {
            // Here to verify whether SignIn is successful or not, no need to make api call.
            // we can use response from initial call to validate response
            Response response = (Response) signInInfo;
            Assert.assertEquals(response.getStatusCode(), 400, "SignIn is successful !!");
            String errorMessage = response.jsonPath().get("message");
            Assert.assertEquals(errorMessage, "User not found", "SignIn is successful !!");
        }

    }
}
