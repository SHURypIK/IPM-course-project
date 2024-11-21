package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
}
