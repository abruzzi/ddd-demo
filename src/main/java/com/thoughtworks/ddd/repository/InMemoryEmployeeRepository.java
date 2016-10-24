package com.thoughtworks.ddd.repository;

import com.thoughtworks.ddd.domain.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryEmployeeRepository implements EmployeeRepository{
    private List<Employee> employees = new ArrayList<>();

    public InMemoryEmployeeRepository() { }

    public InMemoryEmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> findAllAssignable() {
        return employees.stream().
                filter(Employee::isIdeal).
                filter(Employee::isProfessionalService).
                collect(Collectors.toList());
    }

    @Override
    public List<Employee> findBySkill(String skill) {
        return findAllAssignable().stream().
                filter(employee -> employee.hasSKill(skill)).
                collect(Collectors.toList());
    }

    @Override
    public boolean save(Employee employee) {
        return employees.add(employee);
    }

    @Override
    public Employee findByName(String employee) {
        return employees.stream().filter(e -> e.getName().equals(employee)).findFirst().orElse(null);
    }

    @Override
    public Employee employeeOfId(String employeeId) {
        return employees.stream().filter(e -> e.getId().equals(employeeId)).findFirst().orElse(null);
    }
}
