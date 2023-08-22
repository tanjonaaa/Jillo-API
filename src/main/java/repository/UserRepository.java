package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    List<User> all();
    User oneById(int id);
    User oneByEmail(String email);
    void save(User user);
    void update(User user);
    void delete(User user);
}
