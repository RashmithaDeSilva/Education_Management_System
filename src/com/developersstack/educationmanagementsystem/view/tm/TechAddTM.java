package com.developersstack.educationmanagementsystem.view.tm;

import javafx.scene.control.Button;

public class TechAddTM {

    private int code;
    private String name;
    private Button button;


    public TechAddTM() {}

    public TechAddTM(int code, String name, Button button) {
        this.code = code;
        this.name = name;
        this.button = button;
    }


    public int getCode() {return code;}

    public String getName() {return name;}

    public Button getButton() {return button;}


    public void setCode(int code) {this.code = code;}

    public void setName(String name) {this.name = name;}

    public void setButton(Button button) {this.button = button;}
}
