package com.qa.automation.pages;

import com.qa.automation.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Page
public class SignUpPage extends BasePage {

    @FindBy(how = How.ID, using = "firstName")
    public WebElement name;

    @FindBy(how = How.ID, using = "email")
    public WebElement email;

    @FindBy(how = How.ID, using = "password")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//button[text()='Create Account']")
    public WebElement signupButton;

    public String profileName = "//span[text()='%s']";

    @FindBy(how = How.XPATH, using = "//div[text()='This email is already connected to an account.']")
    public WebElement signupErrorMessage;

    @Value("${application.url}")
    private String url;

    @Autowired
    protected WebDriver driver;

    public SignUpPage navigateToSignUpPage() {
        SignUpPage signUpPage = navigateToCreateAccountTab();
        return this;
    }

    public SignUpPage signup(Map<String, String> userInfo) {
        this.navigateToSignUpPage();
        writeText(name, userInfo.get("name"));
        writeText(email, userInfo.get("email"));
        writeText(password, userInfo.get("password"));
        click(signupButton);
        return this;
    }

    public boolean isSignupSuccessful(String name) {
        return isElementDisplayed(By.xpath(String.format(profileName, name)));
    }

    public boolean isSignUpErrorMessageDisplayed() {
        return isElementDisplayed(signupErrorMessage);
    }


}
