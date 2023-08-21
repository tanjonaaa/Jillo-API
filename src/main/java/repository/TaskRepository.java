package repository;

import model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> all();
    Task oneById(int id);
    Task oneByTitle(String title);
    void save(Task task);
    void update(Task task);
    void delete(Task task);
}
