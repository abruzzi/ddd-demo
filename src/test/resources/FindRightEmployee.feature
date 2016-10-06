Feature: Find employee who is assignable
  As a staffing manager
  I want to know who is assignable
  So that I'll be more comfortable when talk to sales

  Background: persona
    Given we have the following employees:
      | name      | currentProject | role  |
      | Juntao    | Beach          | Dev   |
      | Highlight |                | Dev   |
      | Dong      | JetLag         | Dev   |
      | Xiaofeng  | Beach          | Assoc |

  Scenario: Find all PS who is assignable
    Given I am staffing manager
    When I search for people who is assignable
    Then I should get the following names:
      | Juntao    |
      | Highlight |