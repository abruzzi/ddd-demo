package com.thoughtworks.ddd;

import com.thoughtworks.ddd.domain.Project;
import com.thoughtworks.ddd.repository.InMemoryProjectRepository;
import com.thoughtworks.ddd.repository.ProjectRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class FindRightProjectDef {
    private ProjectRepository projectRepository;
    private List<String> skills;
    private String role;
    private List<Project> searchResults;

    @Given("^currently we have the following projects in the pipeline:$")
    public void currentlyWeHaveTheFollowingProjectsInThePipeline(List<ProjectObject> projects) throws Throwable {
        List<Project> converted = projects.stream().map(ProjectObject::toProject).collect(toList());
        projectRepository = new InMemoryProjectRepository(converted);
    }

    @Given("^I am a Professional Service has the following skills:$")
    public void iAmAProfessionalServiceHasTheFollowingSkills(List<String> skills) throws Throwable {
        this.skills = skills;
    }

    @And("^I can play the \"([^\"]*)\" role for now$")
    public void iCanPlayTheRoleForNow(String role) throws Throwable {
        this.role = role;
    }

    @When("^I search for project that I can be assigned$")
    public void iSearchForProjectThatICanBeAssigned() throws Throwable {
        searchResults = projectRepository.findBySkillsAndRole(skills, role);
    }

    @Then("^I should see the following projects:$")
    public void iShouldSeeTheFollowingProjects(List<String> projects) throws Throwable {
        List<String> projectNames = searchResults.stream().map(Project::getName).collect(toList());
        assertThat(projectNames, equalTo(projects));
    }

}
