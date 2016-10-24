package com.thoughtworks.ddd.repository;

import com.thoughtworks.ddd.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAllAssignable();
    List<Employee> findBySkill(String skill);
    boolean save(Employee employee);

    Employee findByName(String employee);

    Employee employeeOfId(String employeeId);
}
