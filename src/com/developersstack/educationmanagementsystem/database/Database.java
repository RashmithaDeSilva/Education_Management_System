package com.developersstack.educationmanagementsystem.database;


import com.developersstack.educationmanagementsystem.model.Student;
import com.developersstack.educationmanagementsystem.model.User;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;

import java.time.LocalDate;
import java.util.ArrayList;


public class Database {

    public static ArrayList<User> userTable = new ArrayList<>();
    public static ArrayList<Student> studentTable = new ArrayList<>();

    static {

        PasswordManager pm = new PasswordManager();

        userTable.add(new User("amal", "kumara", "ak@gmail.com", pm.encode("12345")));
        userTable.add(new User("kamal", "sampath", "ks@gmail.com", pm.encode("12345")));
        userTable.add(new User("nimal", "shantha", "ns@gmail.com", pm.encode("12345")));

        studentTable.add(new Student("S-1", "Saman", LocalDate.parse("2012-05-08"), "Galle"));
        studentTable.add(new Student("S-2", "Kumara", LocalDate.parse("2012-05-08"), "Panadura"));
        studentTable.add(new Student("S-3", "Selvam", LocalDate.parse("2012-05-08"), "Kaluthara"));
    }
}
