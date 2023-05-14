package com.developersstack.educationmanagementsystem.database;

import com.developersstack.educationmanagementsystem.model.User;

import java.util.ArrayList;

public class Database {

    private ArrayList<User> usersArrayList = new ArrayList<User>();

    public ArrayList<User> getUsersArrayList() {
        return usersArrayList;
    }
    public void setUsersArrayList(ArrayList<User> usersArrayList) {
        this.usersArrayList = usersArrayList;
    }
}
