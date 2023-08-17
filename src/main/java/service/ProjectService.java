package service;

import model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAllProjects();
    Project getProjectById(int id);
    Project addProject(Project project);
    Project updateProject(Project project);
    void deleteProject(int id);
}
