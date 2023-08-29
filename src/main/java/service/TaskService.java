package service;

import model.Project;
import model.Task;
import model.User;
import org.springframework.stereotype.Service;
import repository.JdbcProjectRepository;
import repository.JdbcTaskRepository;
import repository.JdbcUserRepository;

import java.util.List;

@Service
public class TaskService {
    private final JdbcTaskRepository repository;
    private final JdbcProjectRepository projectRepository;
    private final JdbcUserRepository userRepository;
    public TaskService(JdbcTaskRepository repository, JdbcProjectRepository projectRepository, JdbcUserRepository userRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }
    public List<Task> getAllTasks() {
        return this.repository.all();
    }
    public Task getTaskById(int id) {
        return this.repository.oneById(id);
    }
    public Task addTask(Task task) {
        Task foundTask = this.repository.oneByUniqueColumn(task.getTitle());
        if(foundTask != null){
            return null;
        }else {
            //Check if the project exists
            Project foundProject = this.projectRepository.oneById(task.getIdProject());
            if(foundProject != null){
                //Check if the user is collaborator in the project
                List<Project> userProjects = this.userRepository.getProjects(task.getIdUser());
                List<Project> filteredProjects = userProjects.stream()
                        .filter((p) -> p.getId().equals(task.getIdProject())).toList();
                System.out.println(filteredProjects);
                if(filteredProjects.size() > 0){
                    this.repository.save(task);
                    return this.repository.oneByUniqueColumn(task.getTitle());
                }else{
                    return null;
                }
            }else {
                return null; //500
            }
        }
    }
    public Task updateTask(Task task) {
        Task foundTask = this.repository.oneById(task.getId());
        if(foundTask != null){
            foundTask.setTitle(task.getTitle());
            foundTask.setDescription(task.getDescription());
            foundTask.setDeadline(task.getDeadline());
            foundTask.setIdUser(task.getIdUser());
            foundTask.setIdProject(task.getIdProject());
            foundTask.setIdStatus(task.getIdStatus());
            this.repository.update(foundTask, foundTask.getId());
            return this.repository.oneById(task.getId());
        }else {
            return null;
        }
    }
    public int deleteTask(int id) {
        Task foundTask = this.repository.oneById(id);
        if(foundTask != null){
            this.repository.delete(id);
            return 1;
        }else {
            return 0;
        }
    }
    public List<User> showAssignees(int id){
        return this.repository.getUsers(id);
    }
}
