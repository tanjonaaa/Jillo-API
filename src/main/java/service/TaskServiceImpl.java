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
        return null;
    }

    @Override
    public Task addTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(int id) {

    }
}
