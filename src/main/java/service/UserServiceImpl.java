package service;

import model.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return this.repository.all();
    }

    @Override
    public User getUserById(int id) {
        User result = this.repository.oneById(id);
        if(result.getId() <= 0){
            return null;
        }else {
            return result;
        }
    }

    @Override
    public void addUser(User user) {
        this.repository.save(user);
    }

    @Override
    public void updateUser(int id) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
