package repository;

import model.Project;

import java.util.List;

public class JdbcProjectRepository implements ProjectRepository{
    @Override
    public List<Project> all() {
        return null;
    }

    @Override
    public Project oneById(int id) {
        return null;
    }

    @Override
    public Project oneByTitle(String title) {
        return null;
    }

    @Override
    public void save(Project project) {

    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(Project project) {

    }
}
