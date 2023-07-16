package com.qa.automation.steps;

import com.github.javafaker.Faker;
import com.qa.automation.constants.ApplicationConstants;
import com.qa.automation.services.SignupService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@CucumberContextConfiguration
@SpringBootTest
public class SignUpSteps<T> {

    @Autowired
    private SignupService signupService;

    private Map<String, String> userInfo;
    private T signupInfo;

    @Value("${spring.profiles.active}")
    private String testsType;

    @Given("I want to signup with details")
    public void signup(DataTable userTable) {
        userInfo = userTable.asMaps(String.class, String.class).get(0);
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        Map<String, String> shallowCopy = userInfo.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        shallowCopy.put("email", shallowCopy.get("email").equals("RANDOM_EMAIL") ? email : shallowCopy.get("email"));
        signupInfo = signupService.signup(shallowCopy);
    }

    @Then("I verify if signup is successful")
    public void verifyIfSignupIsSuccessful() throws IOException {
        if (testsType.equals(ApplicationConstants.UI)) {
            boolean result = signupService.isSignupSuccessful(userInfo.get("name"));
            Assert.assertTrue(result, "Signup is not successful !!");
        } else {
            // Here to verify whether SignU is successful or not, no need to make api call.
            // we can use response from initial call to validate response
            Response response = (Response) signupInfo;
            Assert.assertEquals(response.getStatusCode(), 201, "Signup is not successful !!");
            String name = response.jsonPath().get("payload.user.profile.firstName");
            Assert.assertEquals(name, userInfo.get("name"), "Signup is not successful !!");
        }
    }

    @Then("I verify that error message is displayed")
    public void verifySignUpErrorMessage() {
        if (testsType.equals(ApplicationConstants.UI)) {
            boolean result = signupService.isSignUpErrorMessageDisplayed();
            Assert.assertTrue(result, "Signup error message is not displayed!");
        } else {
            // Here to verify whether SignU is successful or not, no need to make api call.
            // we can use response from initial call to validate response
            Response response = (Response) signupInfo;
            Assert.assertEquals(response.getStatusCode(), 400, "Signup is successful !!");
            String errorMessage = response.jsonPath().get("message[0].constraints[0]");
            Assert.assertEquals(errorMessage, "This email is already connected to an account", "Signup is successful !!");
        }
    }
}
