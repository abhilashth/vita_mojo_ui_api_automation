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
public class SignInPage extends BasePage {

    @FindBy(how = How.ID, using = "email")
    public WebElement email;

    @FindBy(how = How.ID, using = "password")
    public WebElement password;

    @FindBy(how = How.XPATH, using = "//button[text()='Login']")
    public WebElement signInButton;

    public String profileName = "//span[text()='%s']";

    @FindBy(how = How.XPATH, using = "//div[text()='User not found']")
    public WebElement signInErrorMessage;


    @Value("${application.url}")
    private String url;

    @Autowired
    protected WebDriver driver;

    public SignInPage navigateToSignInPage() {
        SignInPage signInPage = navigateToLoginTab();
        return this;
    }

    public SignInPage signIn(Map<String, String> userInfo) {
        this.navigateToLoginTab();
        writeText(email, userInfo.get("email"));
        writeText(password, userInfo.get("password"));
        click(signInButton);
        return this;
    }

    public boolean isSignInSuccessful(String name) {
        return isElementDisplayed(By.xpath(String.format(profileName, name)));
    }

    public boolean isSignInErrorMessageDisplayed() {
        return isElementDisplayed(signInErrorMessage);
    }

}
