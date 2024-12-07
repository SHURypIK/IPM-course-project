package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ResponsiblePerson {

    private String fio;
    private String phone;
    private String mail;
    private List<String> positions = new ArrayList<>();

    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public List<String> getPositions() {
        return positions;
    }
    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public ResponsiblePerson(String fio, String phone, String mail, List<String> positions) {
        this.fio = fio;
        this.phone = phone;
        this.mail = mail;
        this.positions = positions;
    }
    public ResponsiblePerson() {
    }

    public static ResponsiblePerson getByName(ArrayList<ResponsiblePerson> personArrayList, String fio){
        for(ResponsiblePerson person : personArrayList){
            if(person.getFio().equals(fio))
                return person;
        }
        return null;
    }
}
