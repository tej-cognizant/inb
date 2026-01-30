@smoke
Feature: Simple smoke check

  Scenario: Verify cucumber wiring
    Given the framework is ready
    When I run a cucumber smoke
    Then it should pass
