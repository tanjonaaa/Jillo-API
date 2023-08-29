package service;

import model.Project;
import model.Task;
import model.ToBeIn;
import model.User;
import org.springframework.stereotype.Service;
import repository.JdbcProjectRepository;
import repository.JdbcToBeInRepository;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    private JdbcProjectRepository repository;
    private JdbcToBeInRepository toBeInRepository;
    public ProjectService(JdbcProjectRepository repository, JdbcToBeInRepository toBeInRepository) {

        this.repository = repository;
        this.toBeInRepository = toBeInRepository;
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
            ToBeIn toBeIn = new ToBeIn();
            Project createdProject = this.repository.oneByUniqueColumn(project.getTitle());
            toBeIn.setIdProject(createdProject.getId());
            toBeIn.setIdUser(createdProject.getIdUser());
            toBeIn.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            this.toBeInRepository.save(toBeIn);
            return createdProject;
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

    public List<User> getCollaborators(int id){
        return this.repository.getUsers(id);
    }

    public List<Task> getTasks(int id){ return this.repository.getTasks(id); }

    public List<Task> getTasksByStatus(int id, int statusId){
        return this.repository.getTasksByStatus(id, statusId);
    }
    public void addCollaborator(int id, int userId){
        List<ToBeIn> foundToBeIn = this.toBeInRepository.getByForeignKeys(Arrays.asList(id, userId));
        if(foundToBeIn.size() == 0){
            ToBeIn toBeIn = new ToBeIn();
            toBeIn.setIdProject(id);
            toBeIn.setIdUser(userId);
            toBeIn.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            this.toBeInRepository.save(toBeIn);
        }
    }
}
