package com.thoughtworks.ddd.repository;

import com.thoughtworks.ddd.domain.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> findBySkillsAndRole(List<String> skills, String role);
    boolean save(Project project);
    Project findByName(String project);
    Project projectOfId(String projectId);
}
