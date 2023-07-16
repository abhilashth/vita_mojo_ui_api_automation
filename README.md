# **Single** Framework for both UI and API Automation
**ONE Framework for both UI and API Automation [Selenium, Rest Assured]**

- **Spring Boot** is a popular framework, simplifies the developer by handling most of the routine activities. Configuration, object management is done with simple annotations.
- **Dependency Injection** : Easy to create and inject page objects, test classes, properties etc.,
- **Cucumber** : BDD features for building feature files
- **Selenium** : Automation tool for testing web apps.
- **WebDriver Manager** : Manage web drivers and browsers automatically
- **RestAssured** : RestAssured library for Http calls
- **Page Objects** : Reusable page components and get them injected into Steps and Test classes


## Building Framework
- `mvn clean install`

## Execute Test
### Command Line Method
- `mvn clean test` and Test Reports will be available in target/cucumber-report/cucumber.html

### Via Runner
- Complete Suite can be run using TestRunner file and Test Reports will be available in target/cucumber-report/cucumber.html

### Individual Scenario
- To execute a particular test, navigate to feature file and execute it

### How to execute same feature file/files for UI tests
- In application.properties file, property value for spring.profiles.active should be **UI**

### How to execute same feature file/files for API tests
- In application.properties file, property value for spring.profiles.active should be **API**

## Built With
- SpringBoot
- Cucumber
- Java
- Selenium 
- TestNG - Unit testing framework
- WebDriverManager

