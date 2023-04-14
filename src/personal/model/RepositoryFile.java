package personal.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    // Изменил метод на map2
    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map2(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {

        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        save(users);
        return id;

    }

    // Изменил метод на map2
    private void save(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(mapper.map2(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void UpdateUser(User user) {
        List<User> users = getAllUsers();
        for (User item : users) {
            if (item.getId().equals(user.getId())) {
                item.setFirstName(user.getFirstName());
                item.setLastName(user.getLastName());
                item.setPhone(user.getPhone());

            }

        }
        save(users);
    }
    // Удаление производится через итератор
    @Override
    public void DeleteUser(User user) {
        List<User> users = getAllUsers();
        Iterator<User> useriterator = users.iterator();
        while (useriterator.hasNext()) {
            User nextUser = useriterator.next();
            if (nextUser.getId().equals(user.getId())) {
                useriterator.remove();
            }
        }
        save(users);
    }

}
