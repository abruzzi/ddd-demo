Feature: Find employee by skills
  As a staffing manager
  I want to find employee by skills
  So that I know whether we have the staff or we need to hire new ones

  Background: persona
    Given we have the following employees:
      | name   | currentProject | role | skills     |
      | Juntao | Consulting     | Dev  | Java,Ruby  |
      | Yanyu  | Beach          | Dev  | Ruby       |
      | Jiawei | Beach          | Dev  | Java,Ruby |
      | Momo   | Beach          | Dev  | Python     |

  Scenario: Search by skills
    Given I have a project which require "Ruby" as language
    When I search staff by skill
    Then I should get the following names:
      | Yanyu  |
      | Jiawei |