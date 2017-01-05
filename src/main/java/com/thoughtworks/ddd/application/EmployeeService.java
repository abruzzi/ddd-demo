package com.thoughtworks.ddd.application;

import com.thoughtworks.ddd.domain.model.employee.Employee;
import com.thoughtworks.ddd.domain.model.employee.EmployeeRepository;
import com.thoughtworks.ddd.domain.model.project.Assignment;
import com.thoughtworks.ddd.domain.model.project.AssignmentRepository;

public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private AssignmentRepository assignmentRepository;

    public void getEmployeeDetail(String employeeId) {
        Employee employee = employeeRepository.employeeOfId(employeeId);
        Assignment assignment = assignmentRepository.currentAssignmentFor(employeeId);

    }
}
