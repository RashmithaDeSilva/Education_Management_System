package com.developersstack.educationmanagementsystem.model;

import java.time.LocalDate;

public class Intake {

    private String intakeID;
    private LocalDate startDate;
    private String intakeName;
    private String programID;
    private boolean intakeCompleteness;


    public Intake() {}
    public Intake(String intakeID, LocalDate startDate, String intakeName, String programID, boolean intakeCompleteness) {
        this.intakeID = intakeID;
        this.startDate = startDate;
        this.intakeName = intakeName;
        this.programID = programID;
        this.intakeCompleteness = intakeCompleteness;
    }


    public String getIntakeID() {return intakeID;}
    public LocalDate getStartDate() {return startDate;}
    public String getIntakeName() {return intakeName;}
    public String getProgramID() {return programID;}
    public boolean isIntakeCompleteness() {return intakeCompleteness;}


    public void setIntakeID(String intakeID) {this.intakeID = intakeID;}
    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
    public void setIntakeName(String intakeName) {this.intakeName = intakeName;}
    public void setProgramID(String programID) {this.programID = programID;}
    public void setIntakeCompleteness(boolean intakeCompleteness) {this.intakeCompleteness = intakeCompleteness;}
}
