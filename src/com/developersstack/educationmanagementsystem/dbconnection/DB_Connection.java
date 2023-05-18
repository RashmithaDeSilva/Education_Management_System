package com.developersstack.educationmanagementsystem.dbconnection;

import com.developersstack.educationmanagementsystem.database.Database;
import com.developersstack.educationmanagementsystem.model.Student;
import com.developersstack.educationmanagementsystem.model.Teacher;
import com.developersstack.educationmanagementsystem.model.User;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;

import java.util.ArrayList;
import java.util.Optional;

public class DB_Connection {

    // User Operations
    public void addUser(User user) {
        Database.userTable.add(user);
    }

    public boolean checkUserLoginInformation(String email, String password) {
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

    public boolean checkEmail(String email) {
        Optional<User> selectedUser = Database.userTable.stream().filter(
                e->e.getEmail().equals(email)).findFirst();
        return selectedUser.isPresent();
    }

    public void changePassword(String email, String password) {
        Optional<User> selectedUser = Database.userTable.stream().filter(
                e->e.getEmail().equals(email)).findFirst();
        selectedUser.ifPresent(user -> user.setPassword(new PasswordManager().encode(password)));
    }


    // Student Operations
    public void addStudent(Student student) {Database.studentTable.add(student);}

    public String getLastStudentID() {
        return !Database.studentTable.isEmpty() ?
                Database.studentTable.get(Database.studentTable.size()-1).getId() : "Empty";
    }

    public ArrayList<Student> getStudentTable() {
        return Database.studentTable;
    }

    public void updateStudentDetails(Student student) {
        Optional<Student> selectedStudent = Database.studentTable.stream().filter(
                e->e.getId().equals(student.getId())).findFirst();
        if (selectedStudent.isPresent()) {
            selectedStudent.get().setFullName(student.getFullName());
            selectedStudent.get().setDateOfBirth(student.getDateOfBirth());
            selectedStudent.get().setAddress(student.getAddress());
        }
    }

    public void deleteStudent(Student st) {
        Database.studentTable.remove(st);
    }


    // Teacher Operations
    public void addTeacher(Teacher teacher) {Database.teachersTable.add(teacher);}

    public String getLastTeacherCode() {
        return !Database.teachersTable.isEmpty() ?
                Database.teachersTable.get(Database.teachersTable.size()-1).getCode() : "Empty";
    }

    public ArrayList<Teacher> getTeacherTable() {
        return Database.teachersTable;
    }

    public void updateTeacherDetails(Teacher teacher) {
        Optional<Teacher> selectedTeacher = Database.teachersTable.stream().filter(
                e->e.getCode().equals(teacher.getCode())).findFirst();
        if (selectedTeacher.isPresent()) {
            selectedTeacher.get().setName(teacher.getName());
            selectedTeacher.get().setContactNumber(teacher.getContactNumber());
            selectedTeacher.get().setAddress(teacher.getAddress());
        }
    }

    public void deleteTeacher(Teacher t) {
        Database.teachersTable.remove(t);
    }


    // Program Operations

}
