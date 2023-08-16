package service;

import model.Project;
import org.springframework.stereotype.Service;
import repository.JdbcProjectRepository;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private JdbcProjectRepository repository;
    public ProjectServiceImpl(JdbcProjectRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Project> getAllProjects() {
        return this.repository.all();
    }

    @Override
    public Project getProjectById(int id) {
        return this.repository.oneById(id);
    }

    @Override
    public Project addProject(Project project) {
        return null;
    }

    @Override
    public Project updateProject(Project project) {
        return null;
    }

    @Override
    public void deleteProject(int id) {

    }
}
