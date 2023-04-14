package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.Scanner;
import java.util.List;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run() {
            while (true) {
                try {
            Commands com = Commands.NONE;
                String command = prompt("Введите команду: ");
                com = Commands.valueOf(command.toUpperCase());
                if (com == Commands.EXIT)
                    return;
                switch (com) {
                    case CREATE:
                        create();
                        break;
                    case READ:
                        read();
                        break;
                    case LIST:
                        list();
                        break;
                    case UPDATE:
                        update();
                        break;
                    case DELETE:
                        delete();
                        break;

                }
            }
           catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        } 
    }

    private void delete() throws Exception {
        String id = prompt("Идентификатор пользователя: ");
        User user = userController.readUser(id);
        System.out.println(user);
        userController.DeleteUser(user);
    }

    private void update() throws Exception {
        String id = prompt("Идентификатор пользователя: ");
        User user = userController.readUser(id);
        System.out.println(user);
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.UpdateUser(new User(id, firstName, lastName, phone));
    }

    private void list() {
        List<User> lu = userController.AllUsers();
        for (User item : lu) {
            System.out.println(item);
            System.out.println();
        }
    }

    private void create() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        userController.saveUser(new User(firstName, lastName, phone));
    }

    private void read() throws Exception {
        String id = prompt("Идентификатор пользователя: ");

        User user = userController.readUser(id);
        System.out.println(user);
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in, "Cp866");
        System.out.print(message);
        return in.nextLine();
    }
}
