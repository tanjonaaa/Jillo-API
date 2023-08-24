package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User addUser(User user);
    User updateUser(User user);
    int deleteUser(int id);
}
