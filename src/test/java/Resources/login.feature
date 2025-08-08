Feature: User Login
  As a user
  I want to login to the application
  So that I can access my account

  Scenario: Unsuccessful Login with Incorrect Credentials
    Given I am on the login page
    When I enter username "wronguser" and password "wrongpassword"
    And I click on the login button
    Then I should see an error message

  Scenario: Successful Login with Correct Credentials
    Given I am on the login page
    When I enter username "demouser" and password "testingisfun99"
    And I click on the login button
    Then I should be logged in successfully
