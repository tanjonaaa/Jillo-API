package co.tanjona.man.jillo.controller;

import co.tanjona.man.jillo.model.Task;
import co.tanjona.man.jillo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.tanjona.man.jillo.service.TaskService;

import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(this.service.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id){
        Task task = this.service.getTaskById(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task createdTask = this.service.addTask(task);
        if(createdTask == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task){
        task.setId(id);
        Task updatedTask = this.service.updateTask(task);
        if(updatedTask == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteTask(@PathVariable int id){
        int deletionStatus = this.service.deleteTask(id);
        if(deletionStatus == 0){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> showAssignees(@PathVariable int id){
        return ResponseEntity.ok(this.service.showAssignees(id));
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<List<User>> addAssignee(
            @PathVariable int id,
            @RequestBody int idUser
    ){
        List<User> previousAssignees = this.service.showAssignees(id);
        this.service.addAssignee(id, idUser);
        List<User> nextAssignees = this.service.showAssignees(id);
        if(nextAssignees.size() <= previousAssignees.size()){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(nextAssignees);
    }
}
