package com.developersstack.educationmanagementsystem.database;


import com.developersstack.educationmanagementsystem.model.User;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;

import java.util.ArrayList;


public class Database {

    public static ArrayList<User> userTable = new ArrayList<>();

    static {

        PasswordManager pm = new PasswordManager();

        userTable.add(new User("amal", "kumara", "ak@gmail.com", pm.encode("12345")));
        userTable.add(new User("kamal", "sampath", "ks@gmail.com", pm.encode("12345")));
        userTable.add(new User("nimal", "shantha", "ns@gmail.com", pm.encode("12345")));
    }
}
