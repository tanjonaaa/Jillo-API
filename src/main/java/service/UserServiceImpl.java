package service;

import model.Project;
import model.User;
import org.springframework.stereotype.Service;
import repository.JdbcUserRepository;

import java.util.List;
@Service
public class UserServiceImpl{
    private JdbcUserRepository repository;
    public UserServiceImpl(JdbcUserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return this.repository.all();
    }

    public User getUserById(int id) {
        return this.repository.oneById(id);
    }

    public User addUser(User user) {
        User foundUser = this.repository.oneByUniqueColumn(user.getEmail());
        if(foundUser != null){
            return null;
        }else {
            this.repository.save(user);
            return this.repository.oneByUniqueColumn(user.getEmail());
        }
    }


    public User updateUser(User user) {
        User foundUser = this.repository.oneById(user.getId());
        if(foundUser != null){
            foundUser.setUsername(user.getUsername());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            if(this.repository.oneByUniqueColumn(foundUser.getEmail()) == null){
                this.repository.update(foundUser, user.getId());
                return this.repository.oneById(user.getId());
            }else{
                return null;
            }
        }else {
            return null;
        }
    }
    public int deleteUser(int id) {
        User foundUser = this.repository.oneById(id);
        if(foundUser != null){
            this.repository.delete(foundUser.getId());
            return 1;
        }else {
            return 0;
        }
    }
    public List<Project> getProjects(int id){
        return this.repository.getProjects(id);
    }
}
