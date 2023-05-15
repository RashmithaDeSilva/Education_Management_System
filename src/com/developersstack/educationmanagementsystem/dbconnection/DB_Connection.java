package com.developersstack.educationmanagementsystem.dbconnection;

import com.developersstack.educationmanagementsystem.database.Database;
import com.developersstack.educationmanagementsystem.model.User;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;

import java.util.Optional;

public class DB_Connection {

    public void addUser(User user) {
        Database.userTable.add(user);
    }

    public boolean checkUserLogingInfomation(String email, String password) {
        /*for(User u : Database.userTable) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                //System.out.println(u.toString());
                return true;
            }
        }
        return false;*/

        PasswordManager pm = new PasswordManager();

        // Same code without loops
        Optional<User> selectedUser = Database.userTable.stream().filter(e->e.getEmail().equals(email)).findFirst();
        // return selectedUser.map(user -> user.getPassword().equals(password)).orElse(false);
        return selectedUser.filter(user -> pm.checkPassword(password, user.getPassword())).isPresent();
    }
}
