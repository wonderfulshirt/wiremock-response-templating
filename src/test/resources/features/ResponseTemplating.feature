@demo
Feature: Wiremock response templating demo

  Scenario: Wiremock response contains the value supplied in the request - A1
    Given a request has a header test with the value A1
    When the service is called
    Then the status code is 200
    And the response includes the following
      | suppliedValueIs   | A1    |
      | hardcodedResponse | false |

  Scenario: Wiremock response contains the value supplied in the request - A2
    Given a request has a header test with the value A2
    When the service is called
    Then the status code is 200
    And the response includes the following
      | suppliedValueIs   | A2    |
      | hardcodedResponse | false |

  Scenario: Wiremock response contains the value supplied in the request - X1
    Given a request has a header test with the value X1
    When the service is called
    Then the status code is 200
    And the response includes the following
      | suppliedValueIs   | X1   |
      | hardcodedResponse | true |

  Scenario: Wiremock response contains the value supplied in the request - X2
    Given a request has a header test with the value X2
    When the service is called
    Then the status code is 200
    And the response includes the following
      | suppliedValueIs   | X2   |
      | hardcodedResponse | true |