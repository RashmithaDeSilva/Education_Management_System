package com.developersstack.educationmanagementsystem.model;

public class Teacher {

    private String code;
    private String name;
    private String contactNumber;
    private String address;


    public Teacher() {}
    public Teacher(String code, String name, String contactNumber, String address) {
        this.code = code;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }


    public String getCode() {return code;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getContactNumber() {return contactNumber;}


    public void setCode(String code) {this.code = code;}
    public void setName(String name) {this.name = name;}
    public void setAddress(String address) {this.address = address;}
    public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}
}
