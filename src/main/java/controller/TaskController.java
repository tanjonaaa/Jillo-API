package controller;

import model.Task;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Task> getAllTasks(){
        return this.service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id){
        return this.service.getTaskById(id);
    }

    @PostMapping("")
    public Task addTask(@RequestBody Task Task){
        return this.service.addTask(Task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task Task){
        Task.setId(id);
        return this.service.updateTask(Task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        this.service.deleteTask(id);
    }
}
