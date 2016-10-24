package com.thoughtworks.ddd.port.adaptor.persistence;

import com.thoughtworks.ddd.domain.model.project.Project;
import com.thoughtworks.ddd.domain.model.project.ProjectRepository;

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
                filter(project -> project.hasOpenRoleFor(role)).
                collect(Collectors.toList());
    }

    @Override
    public boolean save(Project project) {
        return projects.add(project);
    }

    @Override
    public Project findByName(String project) {
        return projects.stream().filter(p -> p.getName().equals(project)).findFirst().orElse(null);
    }

    @Override
    public Project projectOfId(String projectId) {
        return projects.stream().filter(p -> p.getId().equals(projectId)).findFirst().orElse(null);
    }
}
