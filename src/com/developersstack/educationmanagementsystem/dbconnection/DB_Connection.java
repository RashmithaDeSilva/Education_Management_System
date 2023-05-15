package com.developersstack.educationmanagementsystem.dbconnection;

import com.developersstack.educationmanagementsystem.database.Database;
import com.developersstack.educationmanagementsystem.model.User;

public class DB_Connection {

    public void addUser(User user) {
        Database.userTable.add(user);
    }

    public boolean checkUserLogingInfomation(String email, String password) {
        for(User u : Database.userTable) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
