package com.developersstack.educationmanagementsystem.database;


import com.developersstack.educationmanagementsystem.model.User;
import java.util.ArrayList;


public class Database {

    private static ArrayList<User> userTable = new ArrayList<>();


    public void addUser(User user) {
        userTable.add(user);
    }

//    public User getUser(String email) {
//        for (User user : userTable) {
//            if (user.getEmail().equals(email)) {
//                return user;
//            }
//        }
//
//    }
}
