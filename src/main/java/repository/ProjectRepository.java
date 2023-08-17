package repository;

import model.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> all();
    Project oneById(int id);
    Project oneByTitle(String title);
    void save(Project project);
    void update(Project project);
    void delete(Project project);
}
