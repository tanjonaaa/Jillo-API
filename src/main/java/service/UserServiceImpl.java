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
        return this.repository.oneById(id);
    }

    @Override
    public User addUser(User user) {
        User foundUser = this.repository.oneByEmail(user.getEmail());
        if(foundUser.getId() != 0){
            return null;
        }else {
            System.out.println("Insertion");
            this.repository.save(user);
            return this.repository.oneByEmail(user.getEmail());
        }
    }

    @Override
    public User updateUser(User user) {
        User foundUser = this.repository.oneById(user.getId());
        if(foundUser.getId() != 0){
            foundUser.setUsername(user.getUsername());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            this.repository.update(foundUser);
            return this.repository.oneById(user.getId());
        }else {
            return null;
        }
    }
    
    @Override
    public void deleteUser(int id) {
        User foundUser = this.repository.oneById(id);
        if(foundUser.getId() != 0){
            this.repository.delete(foundUser);
        }else {
            System.out.println("Suppression ratée");
        }
    }
}
