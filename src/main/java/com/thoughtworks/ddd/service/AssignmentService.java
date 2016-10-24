package com.thoughtworks.ddd.service;

import com.thoughtworks.ddd.domain.Assignment;
import com.thoughtworks.ddd.domain.Employee;
import com.thoughtworks.ddd.domain.Project;
import com.thoughtworks.ddd.exception.EmployeeIsNotIdealException;
import com.thoughtworks.ddd.exception.ProjectNoOpenRoleException;
import com.thoughtworks.ddd.repository.AssignmentRepository;

import java.util.Date;

public class AssignmentService {
    private AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public boolean assign(Employee employee, Project project, Date from, Date to) throws EmployeeIsNotIdealException, ProjectNoOpenRoleException {
        if(!employee.isIdeal()) {
            throw new EmployeeIsNotIdealException();
        }

        if(!project.hasOpenRoleFor(employee.getRole())) {
            throw new ProjectNoOpenRoleException();
        }

        return assignmentRepository.save(new Assignment(employee.getId(), project.getId(), from, to));
    }
}
