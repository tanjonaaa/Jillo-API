package service;

import model.Task;
import org.springframework.stereotype.Service;
import repository.JdbcTaskRepository;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService{
    private JdbcTaskRepository repository;
    public TaskServiceImpl(JdbcTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAllTasks() {
        return this.repository.all();
    }

    @Override
    public Task getTaskById(int id) {
        return this.repository.oneById(id);
    }

    @Override
    public Task addTask(Task task) {
        Task foundTask = this.repository.oneByUniqueColumn(task.getTitle());
        if(foundTask != null){
            return null;
        }else {
            System.out.println("Insertion");
            this.repository.save(task);
            return this.repository.oneByUniqueColumn(task.getTitle());
        }
    }

    @Override
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

    @Override
    public int deleteTask(int id) {
        Task foundTask = this.repository.oneById(id);
        if(foundTask != null){
            this.repository.delete(id);
            return 1;
        }else {
            return 0;
        }
    }
}
