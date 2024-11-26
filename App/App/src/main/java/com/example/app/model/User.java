package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class User {

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User() {
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<User> removeAdmins(ArrayList<User> users, ArrayList<Admin> admins){
        for (Admin admin: admins)
            users.remove(admin);
        return users;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Проверка на идентичность ссылок
        if (obj == null || getClass() != obj.getClass()) return false; // Проверка на null и класс
        User user = (User) obj;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password); // Сравнение полей
    }

}
