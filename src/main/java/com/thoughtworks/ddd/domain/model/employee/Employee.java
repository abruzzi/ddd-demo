package com.thoughtworks.ddd.domain.model.employee;

import java.util.List;
import java.util.UUID;

public class Employee {
    private String id;
    private String name;
    private String role;
    private String currentProject;
    private List<String> skills;

    public Employee(String name, String role, String currentProject) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.role = role;
        this.currentProject = currentProject;
    }

    public String getId() {
        return id;
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

    public void updateSkills(List<String> skills) {
        this.skills = skills;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getSkills() {
        return skills;
    }
}
