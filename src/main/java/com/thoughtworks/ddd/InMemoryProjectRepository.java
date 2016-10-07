package com.thoughtworks.ddd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryProjectRepository implements ProjectRepository {
    private List<Project> projects = new ArrayList<>();

    public InMemoryProjectRepository() {
    }

    public InMemoryProjectRepository(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public List<Project> findBySkillsAndRole(List<String> skills, String role) {
        return projects.stream().
                filter(project -> skills.contains(project.getTechStack())).
                filter(project -> project.getOpenRoles().contains(role)).
                collect(Collectors.toList());
    }
}
