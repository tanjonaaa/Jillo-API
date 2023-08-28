package controller;

import model.Project;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import service.UserServiceImpl;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        User user = this.service.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User createdUser = this.service.addUser(user);
        if(createdUser == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        user.setId(id);
        User updatedUser = this.service.updateUser(user);
        if(updatedUser == null){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable int id){
        int deleted = this.service.deleteUser(id);
        if(deleted == 0){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Project>> getProjects(@PathVariable int id){
        return ResponseEntity.ok(this.service.getProjects(id));
    }
}
