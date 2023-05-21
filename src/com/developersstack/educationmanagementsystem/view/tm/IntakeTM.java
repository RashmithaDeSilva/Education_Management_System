package com.developersstack.educationmanagementsystem.view.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class IntakeTM {

    private String intakeID;
    private String startDate;
    private String intakeName;
    private String programID;
    private boolean intakeCompleteness;
    private Button btn;


    public IntakeTM() {}
    public IntakeTM(String intakeID, String startDate, String intakeName,
                    String programID, boolean intakeCompleteness, Button btn) {
        this.intakeID = intakeID;
        this.startDate = startDate;
        this.intakeName = intakeName;
        this.programID = programID;
        this.intakeCompleteness = intakeCompleteness;
        this.btn = btn;
    }


    public String getIntakeID() {return intakeID;}
    public String getStartDate() {return startDate;}
    public String getIntakeName() {return intakeName;}
    public String getProgramID() {return programID;}
    public boolean isIntakeCompleteness() {return intakeCompleteness;}
    public Button getBtn() {return btn;}

    public void setIntakeID(String intakeID) {this.intakeID = intakeID;}
    public void setStartDate(String startDate) {this.startDate = startDate;}
    public void setIntakeName(String intakeName) {this.intakeName = intakeName;}
    public void setProgramID(String programID) {this.programID = programID;}
    public void setIntakeCompleteness(boolean intakeCompleteness) {this.intakeCompleteness = intakeCompleteness;}
    public void setBtn(Button btn) {this.btn = btn;}

}
