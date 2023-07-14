package com.qa.automation.steps;


import com.qa.automation.services.SignInService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.Map;


public class SignInSteps {

    @Autowired
    private SignInService signInService;

    Map<String, String> userInfo;

    @Given("I want to signIn with valid details")
    public void signIn(DataTable userTable) {
        userInfo = userTable.asMaps(String.class, String.class).get(0);
        signInService.signIn(userInfo);
    }

    @Then("I verify if signIn is successful")
    public void verifyIfSignInIsSuccessful() {
        boolean result = signInService.isSignInSuccessful(userInfo.get("name"));
        Assert.assertTrue(result, "Signup is not successful !!");
    }

    @Then("I verify that SignIn error message is displayed")
    public void verifySignInErrorMessage() {
        boolean result = signInService.isSignInErrorMessageDisplayed();
        Assert.assertTrue(result, "Signup error message is not displayed!");
    }
}
