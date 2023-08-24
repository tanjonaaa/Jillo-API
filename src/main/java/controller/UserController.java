package controller;

import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    UserService service;

    public UserController(UserService service) {
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
}
