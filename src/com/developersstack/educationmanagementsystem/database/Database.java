package com.developersstack.educationmanagementsystem.database;


import com.developersstack.educationmanagementsystem.model.User;
import java.util.ArrayList;


public class Database {

    public static ArrayList<User> userTable = new ArrayList<>();

    static {
        userTable.add(new User("amal", "kumara", "ak@gmail.com", "12345"));
        userTable.add(new User("kamal", "sampath", "ks@gmail.com", "12345"));
        userTable.add(new User("nimal", "shantha", "ns@gmail.com", "12345"));
    }
}
