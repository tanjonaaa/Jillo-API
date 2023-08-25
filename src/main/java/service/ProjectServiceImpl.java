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
        Project foundProject = this.repository.oneByTitle(project.getTitle());
        if(foundProject != null){
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
        if(foundProject != null){
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
    public int deleteProject(int id) {
        Project foundProject = this.repository.oneById(id);
        if(foundProject != null){
            this.repository.delete(foundProject);
            return 1;
        }else {
            return 0;
        }
    }
}
