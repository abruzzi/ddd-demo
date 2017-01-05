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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Employee juntao = prepareAJavaDev("Juntao");
        Project beach = prepareAJavaProject("Beach");

        projectService.assignEmployeeToProject(juntao.getId(), beach.getId(), new Date(), new Date());

        List<Assignment> assignments = beach.getAssignments();
        assertThat(assignments.size(), equalTo(1));
    }

    @Test(expected = ProjectNotSuitableForEmployeeException.class)
    public void should_throw_exception_when_employee_skill_doesnt_match() throws EmployeeHasAlreadyAssignedException, ProjectNotSuitableForEmployeeException {
        Employee juntao = prepareAJavaDev("Juntao");
        Project beach = prepareARubyProject("Beach");

        projectService.assignEmployeeToProject(juntao.getId(), beach.getId(), new Date(), new Date());
    }

    @Test(expected = EmployeeHasAlreadyAssignedException.class)
    public void should_throw_exception_when_employee_is_assigned_before() throws EmployeeHasAlreadyAssignedException, ProjectNotSuitableForEmployeeException, ParseException {
        Employee juntao = prepareAJavaDev("Juntao");
        Project huawei = prepareAJavaProject("Huawei");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = simpleDateFormat.parse("2016-10-10");
        Date end = simpleDateFormat.parse("2017-2-1");

        projectService.assignEmployeeToProject(juntao.getId(), huawei.getId(), start, end);

        List<Assignment> assignments = huawei.getAssignments();
        assertThat(assignments.size(), equalTo(1));

        Project beach = prepareAJavaProject("Beach");

        projectService.assignEmployeeToProject(juntao.getId(), beach.getId(), new Date(), new Date());
    }

    private Employee prepareAJavaDev(String name) {
        Employee juntao = new Employee(name, "Dev", null);
        juntao.updateSkills(Collections.singletonList("Java"));
        employeeRepository.save(juntao);

        return juntao;
    }

    private Project prepareAJavaProject(String project) {
        return prepareAProject(project, "Java");
    }

    private Project prepareARubyProject(String project) {
        return prepareAProject(project, "Ruby");
    }

    private Project prepareAProject(String project, String techStack) {
        Project proj = new Project(project, techStack, null);
        projectRepository.save(proj);
        return proj;
    }
}