package com.developersstack.educationmanagementsystem.database;


import com.developersstack.educationmanagementsystem.model.*;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;

import java.time.LocalDate;
import java.util.ArrayList;


public class Database {

    public static ArrayList<User> userTable = new ArrayList<>();
    public static ArrayList<Student> studentTable = new ArrayList<>();
    public static ArrayList<Teacher> teachersTable = new ArrayList<>();
    public static ArrayList<Program> programsTable = new ArrayList<>();
    public static ArrayList<Intake> intakesTable = new ArrayList<>();

    static {

        PasswordManager pm = new PasswordManager();

        userTable.add(new User("amal", "kumara", "ak@gmail.com", pm.encode("12345")));
        userTable.add(new User("kamal", "sampath", "ks@gmail.com", pm.encode("12345")));
        userTable.add(new User("nimal", "shantha", "ns@gmail.com", pm.encode("12345")));

        studentTable.add(new Student("S-1", "Saman", LocalDate.parse("2012-05-08"), "Galle"));
        studentTable.add(new Student("S-2", "Kumara", LocalDate.parse("2012-05-08"), "Panadura"));
        studentTable.add(new Student("S-3", "Selvam", LocalDate.parse("2012-05-08"), "Kaluthara"));

        teachersTable.add(new Teacher("T-1", "Sanduni", "+94123456789", "Galle"));
        teachersTable.add(new Teacher("T-2", "Saman", "+94123456789", "Colombo"));
        teachersTable.add(new Teacher("T-3", "Niwarthan", "+94123456789", "Panadura"));

        programsTable.add(new Program("P-1", "CMJD", new String[]{"Java", "PHP", "Python"}, "T-1", 35000));
        programsTable.add(new Program("P-2", "JDBC", new String[]{"Java", "Python"}, "T-1", 25000));
        programsTable.add(new Program("P-3", "MASTER", new String[]{"Java"}, "T-1", 15000));

        intakesTable.add(new Intake("I-1", LocalDate.parse("2024-01-10"), "Jan", "P-2", false));
        intakesTable.add(new Intake("I-2", LocalDate.parse("2023-06-10"), "June", "P-3", false));
        intakesTable.add(new Intake("I-3", LocalDate.parse("2023-09-10"), "Sep", "P-1", false));
    }
}
