@usedcars
Feature: Browse used cars

  Background:
    Given I open the ZigWheels homepage for used cars

  Scenario: View used cars in Chennai
    When I go to Used Cars from the More menu
    And I select Chennai used cars
    Then I should see some used cars listed
