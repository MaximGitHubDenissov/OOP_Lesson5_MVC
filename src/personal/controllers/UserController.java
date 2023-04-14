package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;
    private final Validation validation = new Validation();

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        validation.validate(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
    public List<User> AllUsers(){
        return repository.getAllUsers();
    }
    public void UpdateUser(User user){
        validation.validate(user);
        repository.UpdateUser(user);
    }
    public void DeleteUser(User user){
        repository.DeleteUser(user);
    }
}
