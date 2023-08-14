package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void addUser(User user);
    void updateUser(int id);
    void deleteUser(int id);
}
