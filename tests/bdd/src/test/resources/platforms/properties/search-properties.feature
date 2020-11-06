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
    When I search for properties by name "simple"
    Then the list of properties found is
      | propertyName    | propertyValue |
      | simple-property | simple-value  |

  Scenario: search a property by value
    Given an existing module
    And an existing platform with this module
    And the platform has these valued properties
      | name             | value         |
      | simple-property  | simple-value  |
      | another-property | another-value |
    When I search for properties by value "simple"
    Then the list of properties found is
      | propertyName    | propertyValue |
      | simple-property | simple-value  |

  Scenario: search a property by name and value
    Given an existing module
    And an existing platform with this module
    And the platform has these valued properties
      | name                    | value         |
      | simple-property         | simple-value  |
      | another-property        | another-value |
      | another-simple-property | oui           |
    When I search for properties by name "simple" and value "simple"
    Then the list of properties found is
      | propertyName    | propertyValue |
      | simple-property | simple-value  |

  Scenario: search a property by name and value
    Given an existing module
    And an existing platform with this module
    And the platform has these valued properties
      | name                    | value         |
      | simple-property         | simple-value  |
      | another-property        | another-value |
      | another-simple-property | oui           |
    When I search for properties by name "simple" and value "simple"
    Then the list of properties found is
      | propertyName    | propertyValue |
      | simple-property | simple-value  |

  Scenario: trying to search properties without a name or a value should fail
    Given an existing module
    And an existing platform with this module
    And the platform has these valued properties
      | name                    | value         |
      | simple-property         | simple-value  |
      | another-property        | another-value |
      | another-simple-property | oui           |
    When I search for properties by name "simple" and value "simple"
    Then the list of properties found is
      | propertyName    | propertyValue |
      | simple-property | simple-value  |
