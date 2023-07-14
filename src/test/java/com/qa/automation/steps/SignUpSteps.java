package com.qa.automation.steps;

import com.github.javafaker.Faker;
import com.qa.automation.services.SignupService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


import java.util.Map;
import java.util.stream.Collectors;

@CucumberContextConfiguration
@SpringBootTest
public class SignUpSteps {

    @Autowired
    private SignupService signupService;

    Map<String, String> userInfo;

    @Given("I want to signup with valid details")
    public void signup(DataTable userTable) {
        userInfo = userTable.asMaps(String.class, String.class).get(0);
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        Map<String, String> shallowCopy = userInfo.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        shallowCopy.put("email", shallowCopy.get("email").equals("RANDOM_EMAIL") ? email : shallowCopy.get("email"));

        signupService.signup(shallowCopy);
    }

    @Then("I verify if signup is successful")
    public void verifyIfSignupIsSuccessful() {
        boolean result = signupService.isSignupSuccessful(userInfo.get("name"));
        Assert.assertTrue(result, "Signup is not successful !!");
    }

    @Then("I verify that error message is displayed")
    public void verifySignUpErrorMessage() {
        boolean result = signupService.isSignUpErrorMessageDisplayed();
        Assert.assertTrue(result, "Signup error message is not displayed!");
    }
}
