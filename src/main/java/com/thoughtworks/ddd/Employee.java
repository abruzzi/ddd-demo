package com.thoughtworks.ddd;

import java.util.List;

public class Employee {
    private String name;
    private String role;
    private String currentProject;
    private List<String> skills;

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

    public String getRole() {
        return role;
    }

    public String getCurrentProject() {
        return currentProject;
    }

    public boolean isProfessionalService() {
        return !getRole().equals("Assoc");
    }

    public boolean isIdeal() {
        return getCurrentProject().equals("Beach") || getCurrentProject().isEmpty();
    }

    public boolean hasSKill(String skill) {
        return this.skills.contains(skill);
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
