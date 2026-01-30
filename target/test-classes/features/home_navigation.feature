@homepage
Feature: Navigate from homepage

  Background:
    Given I open the ZigWheels homepage

  Scenario: Open Upcoming Bikes from New Bikes menu
    When I navigate to Upcoming Bikes from the New Bikes menu
    Then I should land on the Upcoming Bikes page

  Scenario: Open Used Cars from More menu
    When I navigate to Used Cars from the More menu
    Then I should see used cars in Chennai
