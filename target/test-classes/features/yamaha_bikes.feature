@yamaha
Feature: View Yamaha upcoming bikes under 4 lakhs

  Background:
    Given I launch ZigWheels for Yamaha bikes
    When I go to Upcoming Bikes from the New Bikes menu
  And I filter upcoming bikes under 2 lakhs for Yamaha
    And I choose Yamaha upcoming bikes

  Scenario: List Yamaha bikes under 4 lakhs
    Then I should see Yamaha bikes under 4 lakhs
