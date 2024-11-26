package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class Admin extends User {

    private String accessKey;

    public Admin(String login, String password){
        super(login,password);
    }

    public Admin(String login, String password, String accessKey){
        super(login,password);
        this.accessKey = accessKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public Admin() {
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Проверка на идентичность ссылок
        if (obj == null) return false; // Проверка на null
        if (!(obj instanceof User)) return false; // Проверка, является ли объект User или его подклассом

        User user = (User) obj;
        return Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()); // Сравнение логина и пароля
    }
}
