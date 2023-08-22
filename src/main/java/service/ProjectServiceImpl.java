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
        Project foundProject = this.repository.oneById(id);
        if(foundProject.getId() != 0){
            return foundProject;
        }else {
            return null;
        }
    }

    @Override
    public Project addProject(Project project) {
        Project foundProject = this.repository.oneByTitle(project.getTitle());
        if(foundProject.getId() != 0){
            return null;
        }else {
            System.out.println("Insertion");
            this.repository.save(project);
            return this.repository.oneByTitle(project.getTitle());
        }
    }

    @Override
    public Project updateProject(Project project) {
        Project foundProject = this.repository.oneById(project.getId());
        if(foundProject.getId() != 0){
            foundProject.setTitle(project.getTitle());
            foundProject.setDescription(project.getDescription());
            foundProject.setIdUser(project.getIdUser());
            this.repository.update(foundProject);
            return this.repository.oneById(project.getId());
        }else {
            return null;
        }
    }

    @Override
    public void deleteProject(int id) {
        Project foundProject = this.repository.oneById(id);
        if(foundProject.getId() != 0){
            this.repository.delete(foundProject);
        }else {
            System.out.println("Suppression ratée");
        }
    }
}
