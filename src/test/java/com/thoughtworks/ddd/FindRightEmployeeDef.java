package com.thoughtworks.ddd;

import com.thoughtworks.ddd.domain.Employee;
import com.thoughtworks.ddd.repository.EmployeeRepository;
import com.thoughtworks.ddd.repository.InMemoryEmployeeRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindRightEmployeeDef {
    private EmployeeRepository employeeRepository;
    private List<Employee> employees = new ArrayList<>();
    private String skill;

    @Given("^we have the following employees:$")
    public void weHaveTheFollowingEmployees(List<EmployeeObject> employees) throws Throwable {
        List<Employee> converted = employees.stream().map(EmployeeObject::toEmployee).collect(toList());
        employeeRepository = new InMemoryEmployeeRepository(converted);
    }

    @Given("^I am staffing manager$")
    public void iAmStaffingManager() throws Throwable {
    }

    @When("^I search for people who is assignable$")
    public void iSearchForPeopleWhoIsAssignable() throws Throwable {
        employees = employeeRepository.findAllAssignable();
    }

    @Given("^I have a project which require \"([^\"]*)\" as language$")
    public void iHaveAProjectWhichRequireAsLanguage(String skill) throws Throwable {
        this.skill = skill;
    }

    @When("^I search staff by skill$")
    public void iSearchStaffBySkill() throws Throwable {
        employees = employeeRepository.findBySkill(skill);
    }

    @Then("^I should get the following names:$")
    public void iShouldSeeTheFollowingPeople(List<String> names) throws Throwable {
        List<String> collect = employees.stream().map(Employee::getName).collect(toList());
        assertThat(collect, equalTo(names));
    }

}
