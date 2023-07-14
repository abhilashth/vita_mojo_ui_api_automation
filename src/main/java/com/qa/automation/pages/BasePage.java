package com.qa.automation.pages;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class BasePage {

    @Value("${application.url}")
    private String url;

    @FindBy(how = How.XPATH, using = "//div[@id='single-spa-application-root']//a[@data-test='header-profile-link']")
    public WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//li[text()='Create Account']")
    public WebElement createAccountTab;

    @FindBy(how = How.XPATH, using = "//li[text()='Login']")
    public WebElement loginTab;

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @PostConstruct
    public void init() {
        PageFactory.initElements(this.driver, this);
    }

    public <T> void waitElement(T elementAttr) {
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void click(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            driver
                    .findElement((By) elementAttr)
                    .click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    public <T> void writeText(T elementAttr, String text) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver
                    .findElement((By) elementAttr)
                    .sendKeys(text);
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    public <T> String readText(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            return driver
                    .findElement((By) elementAttr)
                    .getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    public <T> boolean isElementDisplayed(T elementAttr) {
        waitElement(elementAttr);
        if (elementAttr
                .getClass()
                .getName()
                .contains("By")) {
            return driver
                    .findElement((By) elementAttr)
                    .isDisplayed();
        } else {
            return ((WebElement) elementAttr).isDisplayed();
        }
    }

    public SignUpPage navigateToCreateAccountTab() {
        this.driver.get(url);
        click(loginLink);
        click(createAccountTab);
        return new SignUpPage();
    }

    public SignInPage navigateToLoginTab() {
        this.driver.get(url);
        click(loginLink);
        click(loginTab);
        return new SignInPage();
    }
}
