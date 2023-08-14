package controller;

import model.User;
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
    public List<User> getAllUsers(){
        return this.service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return this.service.getUserById(id);
    }

    @PostMapping("")
    public void addUser(@RequestBody User user){
        this.service.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user){
        user.setId(id);
        this.service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        this.service.deleteUser(id);
    }
}
