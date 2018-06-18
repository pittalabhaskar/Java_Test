@LoginProWeb
Feature: Ability to Search and sort results on search page

  Background:
    Given I go to the etsy

  @LoginProWeb
  Scenario: 01 Entering Pro Web
    When I Search for "Sports shoes"
    Then Page loaded with Sports shoes
    When I select sort by price
    Then Page sorted by price
    And Top 10 Prices printed
