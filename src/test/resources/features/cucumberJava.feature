Feature: Account creation

  Background: User navigates to the registration page
    Given I navigate to "Book Store Registration Page"

  @SmokeTest
  Scenario: Create New User Account
    Given I create a new valid account
    Then Login button should exits