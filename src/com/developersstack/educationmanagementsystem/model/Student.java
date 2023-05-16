package com.developersstack.educationmanagementsystem.model;
import java.time.LocalDate;


public class Student {

    private String id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String address;


    public Student() {
    }
    public Student(String id, String fullName, LocalDate dateOfBirth, String address) {
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
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public String getAddress() {
        return address;
    }


    public void setId(String id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
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
