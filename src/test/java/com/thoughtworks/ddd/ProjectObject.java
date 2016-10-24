package com.thoughtworks.ddd;

import com.thoughtworks.ddd.domain.model.project.Project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectObject {
    private String name;
    private String techStack;
    private String openRoles;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
        Map<String, Integer> map = new HashMap<>();
        openRoles.forEach(s -> {
            String[] split = s.split(":");
            map.put(split[0],  Integer.valueOf(split[1]));
        });
        return new Project(name, techStack, map);
    }
}
