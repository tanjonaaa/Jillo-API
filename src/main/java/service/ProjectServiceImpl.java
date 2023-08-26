package service;

import model.Project;
import org.springframework.stereotype.Service;
import repository.JdbcProjectRepository;

import java.util.List;

@Service
public class ProjectServiceImpl{

    private JdbcProjectRepository repository;
    public ProjectServiceImpl(JdbcProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getAllProjects() {
        return this.repository.all();
    }


    public Project getProjectById(int id) {
        return this.repository.oneById(id);
    }


    public Project addProject(Project project) {
        Project foundProject = this.repository.oneByUniqueColumn(project.getTitle());
        if(foundProject != null){
            return null;
        }else {
            this.repository.save(project);
            return this.repository.oneByUniqueColumn(project.getTitle());
        }
    }

    public Project updateProject(Project project) {
        Project foundProject = this.repository.oneById(project.getId());
        if(foundProject != null){
            foundProject.setTitle(project.getTitle());
            foundProject.setDescription(project.getDescription());
            foundProject.setIdUser(project.getIdUser());
            this.repository.update(foundProject, foundProject.getId());
            return this.repository.oneById(project.getId());
        }else {
            return null;
        }
    }

    public int deleteProject(int id) {
        Project foundProject = this.repository.oneById(id);
        if(foundProject != null){
            this.repository.delete(id);
            return 1;
        }else {
            return 0;
        }
    }
}
