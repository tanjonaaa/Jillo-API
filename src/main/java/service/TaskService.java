package service;

import model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(int id);
    Task addTask(Task task);
    Task updateTask(Task task);
    void deleteTask(int id);
}
