Feature: As a User I want to validate Signup and SignIn feature

  Scenario: As I user I should not be allowed to signIn using incorrect details
    Given I want to signIn with valid details
      | name     | email                   | password    |
      | abhilash | abhilash23344@gmail.com | 12234567891 |
    Then I verify that SignIn error message is displayed

  Scenario: As a user I want to signInErrorMessage with valid details
    Given I want to signIn with valid details
      | name     | email               | password |
      | abhilash | abhilash1@gmail.com | 12345678 |
    Then I verify if signIn is successful

  Scenario: As a user I want to verify the successful signup usecase with valid data
    Given I want to signup with valid details
      | name     | email        | password   |
      | abhilash | RANDOM_EMAIL | 1234567891 |
    Then I verify if signup is successful

  Scenario: As I user I should not be allowed to signup using same email address
    Given I want to signup with valid details
      | name     | email              | password   |
      | abhilash | abhilash@gmail.com | 1234567891 |
    Then I verify that error message is displayed


