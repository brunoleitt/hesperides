Feature: Get modules detail

  Background:
    Given an authenticated user

  Scenario: get the detail of an existing module
    Given an existing techno
    And an existing module with this techno
    When I get the module detail
    Then the module detail is successfully retrieved

  Scenario: get the detail of a released module
    Given an existing released techno
    And an existing released module with this techno
    When I get the module detail
    Then the module detail is successfully retrieved

  @require-real-mongo
  Scenario: get the detail of an existing module with the wrong letter case
    Given an existing module
    When I get the module detail with the wrong letter case
    Then the module detail is successfully retrieved

  @require-real-mongo
  Scenario: get the detail of a module whose name contains a slash and a percent sign
    Given an existing module named "toto/tata%titi"
    When I get the module detail
    Then the module detail is successfully retrieved

  Scenario: get the detail of the working copy of a module that doesn't exist
    Given a module that doesn't exist
    When I try to get the module detail
    Then the resource is not found

  Scenario: get the detail of a released module that only exist as working copy
    Given an existing techno
    And an existing module with this techno
    When I try to get the module detail for a module type "release"
    Then the resource is not found

  Scenario: get the detail of a module with an invalid module type
    Given an existing techno
    And an existing module with this techno
    When I try to get the module detail for a module type "unknown"
    Then the request is rejected with a bad request error
