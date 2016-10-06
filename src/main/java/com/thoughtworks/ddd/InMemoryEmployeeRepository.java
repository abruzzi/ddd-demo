package com.thoughtworks.ddd;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryEmployeeRepository implements EmployeeRepository{
    private List<Employee> employees = new ArrayList<>();

    public InMemoryEmployeeRepository() {
    }

    public InMemoryEmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> findAllAssignable() {
        Predicate<Employee> beach = employee -> employee.getCurrentProject().equals("Beach") || employee.getCurrentProject().isEmpty();
        Predicate<Employee> assoc = employee -> !employee.getRole().equals("Assoc");

        return employees.stream().filter(beach).filter(assoc).collect(Collectors.toList());
    }
}
