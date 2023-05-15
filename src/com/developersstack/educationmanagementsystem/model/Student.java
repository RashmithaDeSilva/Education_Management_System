package com.developersstack.educationmanagementsystem.model;

import java.util.Date;

public class Student {

    private String id;
    private String fullName;
    private Date dateOfBirth;
    private String address;

    
    public Student() {
    }
    public Student(String id, String fullName, Date dateOfBirth, String address) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }


    public String getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getAddress() {
        return address;
    }


    public void setId(String id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                '}';
    }
}
