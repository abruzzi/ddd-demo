package com.thoughtworks.ddd;

import java.util.Arrays;
import java.util.Collections;

public class EmployeeObject {
    private String name;
    private String role;
    private String currentProject;
    private String skills;

    public EmployeeObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(String currentProject) {
        this.currentProject = currentProject;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Employee toEmployee() {
        Employee employee = new Employee(name, role, currentProject);
        if(skills != null && skills.contains(",")) {
            employee.setSkills(Arrays.asList(skills.split(",")));
        } else {
            employee.setSkills(Collections.singletonList(skills));
        }
        return employee;
    }
}
