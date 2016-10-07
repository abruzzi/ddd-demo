package com.thoughtworks.ddd;

import java.util.Arrays;
import java.util.List;

public class ProjectObject {
    private String name;
    private String techStack;
    private String openRoles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getOpenRoles() {
        return openRoles;
    }

    public void setOpenRoles(String openRoles) {
        this.openRoles = openRoles;
    }

    public Project toProject() {
        List<String> openRoles = Arrays.asList(this.openRoles.split(","));
        return new Project(name, techStack, openRoles);
    }
}
