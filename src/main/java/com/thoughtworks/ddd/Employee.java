package com.thoughtworks.ddd;

public class Employee {
    private String name;
    private String role;
    private String currentProject;

    public Employee() {
    }

    public Employee(String name, String role, String currentProject) {
        this.name = name;
        this.role = role;
        this.currentProject = currentProject;
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
}
