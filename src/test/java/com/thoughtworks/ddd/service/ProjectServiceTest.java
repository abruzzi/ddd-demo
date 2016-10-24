package com.thoughtworks.ddd.service;

import com.thoughtworks.ddd.application.ProjectService;
import com.thoughtworks.ddd.domain.model.employee.Employee;
import com.thoughtworks.ddd.domain.model.employee.EmployeeRepository;
import com.thoughtworks.ddd.domain.model.project.Assignment;
import com.thoughtworks.ddd.domain.model.project.AssignmentRepository;
import com.thoughtworks.ddd.domain.model.project.Project;
import com.thoughtworks.ddd.domain.model.project.ProjectRepository;
import com.thoughtworks.ddd.exception.EmployeeHasAlreadyAssignedException;
import com.thoughtworks.ddd.exception.ProjectNotSuitableForEmployeeException;
import com.thoughtworks.ddd.port.adaptor.persistence.InMemoryAssignmentRepository;
import com.thoughtworks.ddd.port.adaptor.persistence.InMemoryEmployeeRepository;
import com.thoughtworks.ddd.port.adaptor.persistence.InMemoryProjectRepository;
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
        juntao.updateSkills(Collections.singletonList("Java"));
        employeeRepository.save(juntao);

        Project beach = new Project("Beach", "Java", null);
        projectRepository.save(beach);

        projectService.assignEmployeeToProject(juntao.getId(), beach.getId(), new Date(), new Date());

        List<Assignment> assignments = beach.getAssignments();
        assertThat(assignments.size(), equalTo(1));
    }

}