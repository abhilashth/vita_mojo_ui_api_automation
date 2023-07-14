package com.qa.automation.steps;

import com.qa.automation.annotation.LazyAutowired;
import com.qa.automation.constants.ApplicationConstants;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;


import com.qa.automation.service.ScreenshotService;

public class Hooks {

    @LazyAutowired
    private ScreenshotService screenshotService;

    @LazyAutowired
    private ApplicationContext applicationContext;

    @Value("${spring.profiles.active}")
    private String testsType;

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed() && testsType.equals(ApplicationConstants.UI)) {
            scenario.attach(this.screenshotService.getScreenshot(), "image/png", scenario.getName());
        }
    }

    @After
    public void afterScenario(){
        if (testsType.equals(ApplicationConstants.UI)) {
            this.applicationContext.getBean(WebDriver.class).manage().deleteAllCookies();
        }
    }

}
