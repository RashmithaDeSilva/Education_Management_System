package com.developersstack.educationmanagementsystem.view.tm;

import javafx.scene.control.Button;

public class StudentTM {

    private String id;
    private String name;
    private String dob;
    private String address;
    private Button button;


    public StudentTM() {
    }
    public StudentTM(String id, String name, String dob, String address, Button button) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.button = button;
    }


    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDob() {
        return dob;
    }
    public String getAddress() {
        return address;
    }
    public Button getButton() {
        return button;
    }


    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setButton(Button button) {
        this.button = button;
    }
}
