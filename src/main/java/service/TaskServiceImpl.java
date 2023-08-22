package service;

import model.Task;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService{
    private TaskRepository repository;
    public TaskServiceImpl(TaskRepository repository) {
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
        Task foundTask = this.repository.oneByTitle(task.getTitle());
        if(foundTask.getId() != 0){
            return null;
        }else {
            System.out.println("Insertion");
            this.repository.save(task);
            return this.repository.oneByTitle(task.getTitle());
        }
    }

    @Override
    public Task updateTask(Task task) {
        Task foundTask = this.repository.oneById(task.getId());
        if(foundTask.getId() != 0){
            foundTask.setTitle(task.getTitle());
            foundTask.setDescription(task.getDescription());
            foundTask.setDeadline(task.getDeadline());
            foundTask.setIdUser(task.getIdUser());
            foundTask.setIdProject(task.getIdProject());
            foundTask.setIdStatus(task.getIdStatus());
            this.repository.update(foundTask);
            return this.repository.oneById(task.getId());
        }else {
            return null;
        }
    }

    @Override
    public void deleteTask(int id) {
        Task foundTask = this.repository.oneById(id);
        if(foundTask.getId() != 0){
            this.repository.delete(foundTask);
        }else {
            System.out.println("Suppression ratée");
        }
    }
}
