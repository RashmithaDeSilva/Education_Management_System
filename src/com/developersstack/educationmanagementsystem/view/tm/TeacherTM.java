package com.developersstack.educationmanagementsystem.view.tm;

import javafx.scene.control.Button;

public class TeacherTM {

    private String code;
    private String name;
    private String address;
    private String contactNumber;
    private Button button;


    public TeacherTM() {}
    public TeacherTM(String code, String name, String address, String contactNumber, Button button) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.button = button;
    }


    public String getCode() {return code;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getContactNumber() {return contactNumber;}
    public Button getButton() {return button;}


    public void setCode(String code) {this.code = code;}
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}
    public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}
    public void setButton(Button button) {this.button = button;}
}
