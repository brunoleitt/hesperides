Feature: Search properties

  Background:
    Given an authenticated user

  Scenario: search a property by name
    Given an existing module
    And an existing platform with this module
    And the platform has these valued properties
      | name             | value         |
      | simple-property  | simple-value  |
      | another-property | another-value |
    When I search for properties by name with "mple"
    Then the list of properties found is
      | propertyName    | propertyValue |
      | simple-property | simple-value  |
