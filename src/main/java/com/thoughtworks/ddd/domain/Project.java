package com.thoughtworks.ddd.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Project {
    private final String name;
    private final String techStack;
    private final Map<String, Integer> openRoles;

    private final String id;

    public Project(String name, String techStack, Map<String, Integer> openRoles) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.techStack = techStack;
        this.openRoles = openRoles;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTechStack() {
        return techStack;
    }

    public Map<String, Integer> getOpenRoles() {
        return openRoles;
    }

    public boolean hasOpenRoleFor(String role) {
        return openRoles.containsKey(role);
    }

    public boolean isSuitableFor(Employee employee) {
        return employee.getSkills().contains(getTechStack());
    }

    private List<Assignment> assignments = new ArrayList<>();

    public void addAssignment(Assignment assignment) {
        if(assignments.contains(assignment)) {
            int index = assignments.indexOf(assignment);
            assignments.set(index, assignment);
        } else {
            assignments.add(assignment);
        }
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }
}
