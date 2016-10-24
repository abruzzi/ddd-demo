package com.thoughtworks.ddd.application;

import com.thoughtworks.ddd.domain.model.project.Assignment;
import com.thoughtworks.ddd.domain.model.employee.Employee;
import com.thoughtworks.ddd.domain.model.project.Project;
import com.thoughtworks.ddd.exception.EmployeeHasAlreadyAssignedException;
import com.thoughtworks.ddd.exception.ProjectNotSuitableForEmployeeException;
import com.thoughtworks.ddd.domain.model.project.AssignmentRepository;
import com.thoughtworks.ddd.domain.model.employee.EmployeeRepository;
import com.thoughtworks.ddd.domain.model.project.ProjectRepository;

import java.util.Date;

public class ProjectService {
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private AssignmentRepository assignmentRepository;

    public ProjectService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, AssignmentRepository assignmentRepository) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public boolean assignEmployeeToProject(String employeeId, String projectId, Date start, Date end) throws EmployeeHasAlreadyAssignedException, ProjectNotSuitableForEmployeeException {
        Employee employee = employeeRepository.employeeOfId(employeeId);
        Project project = projectRepository.projectOfId(projectId);

        if(assignmentRepository.hasAssignmentBetween(employeeId, start, end)) {
            throw new EmployeeHasAlreadyAssignedException();
        }

        if(!project.isSuitableFor(employee)) {
            throw new ProjectNotSuitableForEmployeeException();
        }

        Assignment assignment = new Assignment(employeeId, projectId, employee.getRole(), start, end);
        assignmentRepository.save(assignment);

        project.addAssignment(assignment);
        return projectRepository.save(project);
    }
}
