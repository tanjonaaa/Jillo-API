package repository;

import model.User;

import java.util.List;

public interface UserRepository {
    List<User> all();
    User oneById(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
}
