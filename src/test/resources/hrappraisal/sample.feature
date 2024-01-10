Feature: 실행 잘 되나?

  Scenario: 1+2=3
    Given I have a calculator
    When I add 1 and 2
    Then the result should be 3