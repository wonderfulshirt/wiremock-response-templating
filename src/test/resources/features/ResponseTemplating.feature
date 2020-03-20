@demo
Feature: Wiremock response templating demo

  Scenario: Wiremock response contains the value supplied in the request - A1
    Given a request has a header test with the value A1
    When the service is called
    Then the status code is 200
    And the response includes the following
    | suppliedValueIs | A1 |

  Scenario: Wiremock response contains the value supplied in the request - A2
    Given a request has a header test with the value A2
    When the service is called
    Then the status code is 200
    And the response includes the following
      | suppliedValueIs | A2 |