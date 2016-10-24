Feature: Find project
  As a Professional Service
  I want to find project I'm interested
  So I can learn some new stuff in the project

  Background: Projects setup
    Given currently we have the following projects in the pipeline:
      | name        | techStack  | openRoles  |
      | JetLag      | C#         | Dev:1,QA:1 |
      | JetLag      | C#         | QA:1       |
      | Commercial  | Java       | Dev:1      |
      | Residential | Ruby       | Dev:1      |
      | CCAV        | JavaScript | QA:1       |

  Scenario: find suitable project
    Given I am a Professional Service has the following skills:
      | Java       |
      | Ruby       |
      | JavaScript |
    And I can play the "Dev" role for now
    When I search for project that I can be assigned
    Then I should see the following projects:
      | Commercial  |
      | Residential |
