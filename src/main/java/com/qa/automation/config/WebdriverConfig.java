package com.qa.automation.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import com.qa.automation.annotation.LazyConfiguration;
import com.qa.automation.annotation.ThreadScopeBean;

import io.github.bonigarcia.wdm.WebDriverManager;

@LazyConfiguration
public class WebdriverConfig {
	
	@ThreadScopeBean
	@ConditionalOnProperty(name = "browser", havingValue = "firefox")
	protected WebDriver firefoxdriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	@ThreadScopeBean
	@ConditionalOnProperty(name = "browser", havingValue = "chrome")
	protected WebDriver chromedriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(chromeOptions);
	}
	
}
