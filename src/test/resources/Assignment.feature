Feature: Assign employee to project
  As a staffing manager
  I want to assign employee to suitable project
  So that the project can run

  Scenario: assign employee on beach
    Given project "Commercial" has "5" open roles for "Dev"
    And "Juntao Qiu" who has skill "Java" is on project "Beach" now
    When I assign him to project "Commercial"
    Then "Juntao Qiu" should be on project "Commercial" now
    And project "Commercial" should has "4" open roles for "Dev"
