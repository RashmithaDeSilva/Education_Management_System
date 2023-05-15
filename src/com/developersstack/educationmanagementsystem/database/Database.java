package com.developersstack.educationmanagementsystem.database;


import com.developersstack.educationmanagementsystem.model.User;
import java.util.ArrayList;


public class Database {

    private static ArrayList<User> userTable = new ArrayList<>();


    public void addUser(User user) {
        userTable.add(user);
    }

    public boolean checkUserLogingInfomation(String email, String password) {
        for(User u : userTable) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}