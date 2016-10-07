package com.thoughtworks.ddd;

import java.util.List;

public class Project {
    private final String name;
    private final String techStack;
    private final List<String> openRoles;

    public Project(String name, String techStack, List<String> openRoles) {
        this.name = name;
        this.techStack = techStack;
        this.openRoles = openRoles;
    }

    public String getName() {
        return name;
    }

    public String getTechStack() {
        return techStack;
    }

    public List<String> getOpenRoles() {
        return openRoles;
    }
}
