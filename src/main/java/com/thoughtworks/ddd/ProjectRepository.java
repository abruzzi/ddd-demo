package com.thoughtworks.ddd;

import java.util.List;

public interface ProjectRepository {
    List<Project> findBySkillsAndRole(List<String> skills, String role);
}
