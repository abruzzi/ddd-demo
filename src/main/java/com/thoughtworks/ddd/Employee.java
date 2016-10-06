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

    public List<String> getSkills() {
        return skills;
    }
}
