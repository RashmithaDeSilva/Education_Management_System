package com.developersstack.educationmanagementsystem.model;

public class Registration {

    private String id;
    private String studentID;
    private String program;
    private boolean paidOrNot;


    public Registration() {}
    public Registration(String id, String studentID, String program, boolean paidOrNot) {
        this.id = id;
        this.studentID = studentID;
        this.program = program;
        this.paidOrNot = paidOrNot;
    }


    public String getId() {return id;}
    public String getStudentID() {return studentID;}
    public String getProgram() {return program;}
    public boolean isPaidOrNot() {return paidOrNot;}


    public void setId(String id) {this.id = id;}
    public void setStudentID(String studentID) {this.studentID = studentID;}
    public void setProgram(String program) {this.program = program;}
    public void setPaidOrNot(boolean paidOrNot) {this.paidOrNot = paidOrNot;}
}
