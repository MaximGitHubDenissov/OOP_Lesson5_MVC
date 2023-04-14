package personal.controllers;

import java.util.regex.Pattern;

import personal.model.User;

public class Validation {
    final String regex = "^\\S+$";
    final String reqexnum = "^\\d+$";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Pattern digit = Pattern.compile(reqexnum, Pattern.MULTILINE); 
    void validate(User user){
        if(!pattern.matcher(user.getFirstName()).find()){
            throw new RuntimeException("Поле Имя не должно быть пустым");
        }
        if(!pattern.matcher(user.getLastName()).find()){
            throw new RuntimeException(" Поле Фамилия не должно быть пустым");
        }
        if(!pattern.matcher(user.getPhone()).find()){
            throw new RuntimeException("Поле Номер телефона не должно быть пустым");
        }
        if(!digit.matcher(user.getPhone()).find()){
            throw new RuntimeException("Поле Номер телефона должно включать цифры");
    }
}
}
