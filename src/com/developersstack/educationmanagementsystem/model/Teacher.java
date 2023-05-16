package com.developersstack.educationmanagementsystem.model;

public class Teacher {

    private String code;
    private String name;
    private String address;
    private String contactNumber;


    public Teacher() {}
    public Teacher(String code, String name, String address, String contactNumber) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
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
