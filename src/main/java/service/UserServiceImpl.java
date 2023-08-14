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
        User foundUser = this.repository.oneByEmail(user.getEmail());
        if(foundUser.getId() <= 0){
            this.repository.save(user);
        }else {
            System.out.println("Cet email est déjà pris");
        }
    }

    @Override
    public void updateUser(User user) {
        User foundUser = this.repository.oneById(user.getId());
        if(foundUser != null){
            foundUser.setUsername(user.getUsername());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            this.repository.update(foundUser);
        }else {
            System.out.println("L'utilisateur que vous voulez modifier n'existe pas");
        }
    }

    @Override
    public void deleteUser(int id) {

    }
}
