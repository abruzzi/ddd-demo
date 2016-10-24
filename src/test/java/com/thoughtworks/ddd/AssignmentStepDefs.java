package com.thoughtworks.ddd;

import com.thoughtworks.ddd.domain.Assignment;
import com.thoughtworks.ddd.domain.Employee;
import com.thoughtworks.ddd.domain.Project;
import com.thoughtworks.ddd.repository.*;
import com.thoughtworks.ddd.service.ProjectService;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AssignmentStepDefs {
    private ProjectRepository projectRepository;
    private EmployeeRepository employeeRepository;
    private AssignmentRepository assignmentRepository;
    private ProjectService projectService;
    private Employee current;

    @Before
    public void setUp() {
        projectRepository = new InMemoryProjectRepository();
        employeeRepository = new InMemoryEmployeeRepository();
        assignmentRepository = new InMemoryAssignmentRepository();
        projectService = new ProjectService(employeeRepository, projectRepository, assignmentRepository);
    }

    @When("^I assign him to project \"([^\"]*)\"$")
    public void iAssignHimToProject(String project) throws Throwable {
        Project proj = projectRepository.findByName(project);

        Date now = new Date();
        projectService.assignEmployeeToProject(current.getId(), proj.getId(), now, now);
    }

    @Given("^project \"([^\"]*)\" has \"([^\"]*)\" open roles for \"([^\"]*)\"$")
    public void projectHasOpenRolesFor(String project, String number, String role) throws Throwable {
        Project proj = new Project(project, "Java", new HashMap<String, Integer>() {{
            put(role, Integer.valueOf(number));
        }});

        projectRepository.save(proj);
    }

    @And("^project \"([^\"]*)\" should has \"([^\"]*)\" open roles for \"([^\"]*)\"$")
    public void projectShouldHasOpenRolesFor(String project, String number, String role) throws Throwable {
        Project proj = projectRepository.findByName(project);
        Integer quantity = proj.getOpenRoles().get(role);
        assertThat(quantity, equalTo(Integer.valueOf(number)));
    }

    @Then("^\"([^\"]*)\" should be on project \"([^\"]*)\" now$")
    public void shouldBeOnProjectNow(String employee, String project) throws Throwable {
        Employee ps = employeeRepository.findByName(employee);
        Project byName = projectRepository.findByName(project);
        System.err.println(ps.getId());
        Assignment assignment = assignmentRepository.currentAssignmentFor(ps.getId());
        assertThat(assignment.getProjectId(), equalTo(byName.getId()));
    }

    @And("^\"([^\"]*)\" who has skill \"([^\"]*)\" is on project \"([^\"]*)\" now$")
    public void whoHasSkillIsOnProjectNow(String employee, String skill, String project) throws Throwable {
        Employee ps = new Employee(employee, "Dev", project);
        ps.setSkills(Collections.singletonList(skill));
        employeeRepository.save(ps);
        current = ps;
    }
}
