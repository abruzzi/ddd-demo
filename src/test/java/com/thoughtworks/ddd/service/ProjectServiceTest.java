package com.thoughtworks.ddd.service;

import com.thoughtworks.ddd.domain.Assignment;
import com.thoughtworks.ddd.domain.Employee;
import com.thoughtworks.ddd.domain.Project;
import com.thoughtworks.ddd.exception.EmployeeHasAlreadyAssignedException;
import com.thoughtworks.ddd.exception.ProjectNotSuitableForEmployeeException;
import com.thoughtworks.ddd.repository.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ProjectServiceTest {
    private ProjectService projectService;
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private AssignmentRepository assignmentRepository;

    @Before
    public void setUp() {
        employeeRepository = new InMemoryEmployeeRepository();
        projectRepository = new InMemoryProjectRepository();
        assignmentRepository = new InMemoryAssignmentRepository();

        projectService = new ProjectService(employeeRepository, projectRepository, assignmentRepository);
    }

    @Test
    public void should_assign_employee_to_project() throws EmployeeHasAlreadyAssignedException, ProjectNotSuitableForEmployeeException {
        Employee juntao = new Employee("Juntao", "Dev", null);
        juntao.setSkills(Collections.singletonList("Java"));
        employeeRepository.save(juntao);

        Project beach = new Project("Beach", "Java", null);
        projectRepository.save(beach);

        projectService.assignEmployeeToProject(juntao.getId(), beach.getId(), new Date(), new Date());

        List<Assignment> assignments = beach.getAssignments();
        assertThat(assignments.size(), equalTo(1));
    }

}