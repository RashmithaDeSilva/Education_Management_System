package com.developersstack.educationmanagementsystem.view.tm;

import javafx.scene.control.Button;

public class ProgramTM {

    private String code;
    private String name;
    private String[] technologies;
    private String teacherID;
    private double cost;
    private Button btn;


    public ProgramTM() {}
    public ProgramTM(String code, String name, String teacherID, String[] technologies, double cost, Button btn) {
        this.code = code;
        this.name = name;
        this.technologies = technologies;
        this.teacherID = teacherID;
        this.cost = cost;
        this.btn = btn;
    }


    public String getCode() {return code;}
    public String getName() {return name;}
    public String[] getTechnologies() {return technologies;}
    public String getTeacherID() {return teacherID;}
    public double getCost() {return cost;}
    public Button getBtn() {return btn;}


    public void setCode(String code) {this.code = code;}
    public void setName(String name) {this.name = name;}
    public void setTechnologies(String[] technologies) {this.technologies = technologies;}
    public void setTeacherID(String teacherID) {this.teacherID = teacherID;}
    public void setCost(double cost) {this.cost = cost;}
    public void setBtn(Button btn) {this.btn = btn;}
}
