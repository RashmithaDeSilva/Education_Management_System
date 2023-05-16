package com.developersstack.educationmanagementsystem.model;

public class Program {

    private String code;
    private String name;
    private String[] technologies;
    private String teacherID;
    private double cost;


    public Program() {}
    public Program(String code, String name, String[] technologies, String teacherID, double cost) {
        this.code = code;
        this.name = name;
        this.technologies = technologies;
        this.teacherID = teacherID;
        this.cost = cost;
    }


    public String getCode() {return code;}
    public String getName() {return name;}
    public String[] getTechnologies() {return technologies;}
    public String getTeacherID() {return teacherID;}
    public double getCost() {return cost;}


    public void setCode(String code) {this.code = code;}

    public void setName(String name) {this.name = name;}

    public void setTechnologies(String[] technologies) {this.technologies = technologies;}

    public void setTeacherID(String teacherID) {this.teacherID = teacherID;}

    public void setCost(double cost) {this.cost = cost;}
}
