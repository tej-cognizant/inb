@upcoming
Feature: Upcoming bikes navigation

  Background:
    Given I open the ZigWheels homepage
    And I navigate to Upcoming Bikes from the New Bikes menu

  Scenario: View Yamaha upcoming bikes under 2 lakhs
    When I filter upcoming bikes under 2 lakhs
    And I open Yamaha upcoming bikes
    Then I should see Yamaha upcoming bikes
