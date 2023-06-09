package com.developersstack.educationmanagementsystem.dbconnection;

import com.developersstack.educationmanagementsystem.database.Database;
import com.developersstack.educationmanagementsystem.model.*;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;
import javafx.collections.ObservableList;

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

    public String getUserName(String email) {
        Optional<User> selectedUser = Database.userTable.stream().filter(
                e->e.getEmail().equals(email)).findFirst();
        return selectedUser.map(user -> user.getFirstName() + " " + user.getLastName()).orElse("empty");
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

    public ArrayList<String> getTeacherIDsAndNames() {
        ArrayList<String> teacherArray = new ArrayList<>();
        for (Teacher t : Database.teachersTable) {
            teacherArray.add(t.getCode() + ". " + t.getName());
        }
        return teacherArray;
    }


    // Program Operations

    public String getLastProgramCode() {
        return !Database.programsTable.isEmpty() ?
                Database.programsTable.get(Database.programsTable.size()-1).getCode() : "Empty";
    }

    public void addProgram(Program program) {Database.programsTable.add(program);}

    public ArrayList<Program> getProgramTable() {return Database.programsTable;}

    public void deleteProgram(Program program) {Database.programsTable.remove(program);}

    public String[] getTechnologies(String code) {
        Optional<Program> selectedProgram = Database.programsTable.stream().filter(e->e.getCode().equals(code)).findFirst();
        return selectedProgram.map(Program::getTechnologies).orElse(null);
    }

    public void updateProgram(Program program) {
        Optional<Program> selectedProgram = Database.programsTable.stream().filter(
                e->e.getCode().equals(program.getCode())).findFirst();
        if (selectedProgram.isPresent()) {
            selectedProgram.get().setName(program.getName());
            selectedProgram.get().setCost(program.getCost());
            selectedProgram.get().setTeacherID(program.getTeacherID());
            selectedProgram.get().setTechnologies(program.getTechnologies());
        }
    }


    // Intake Operations

    public String getlastIntakeCode() {
        return  !Database.intakesTable.isEmpty() ?
                Database.intakesTable.get(Database.intakesTable.size()-1).getIntakeID() : "Empty";
    }

    public void addIntake(Intake intake) {Database.intakesTable.add(intake);}

    public ArrayList<Intake> getIntakeTable() {return Database.intakesTable;}

    public void deleteIntake(Intake intake) {Database.intakesTable.remove(intake);}

    public void updateIntake(Intake intake) {
        Optional<Intake> selectedIntake = Database.intakesTable.stream().filter(
                e->e.getIntakeID().equals(intake.getIntakeID())).findFirst();
        if (selectedIntake.isPresent()) {
            selectedIntake.get().setIntakeName(intake.getIntakeName());
            selectedIntake.get().setStartDate(intake.getStartDate());
            selectedIntake.get().setProgramID(intake.getProgramID());
        }
    }


    //  Registration

    public String getLastRegistrationID() {
        return !Database.registrationsTable.isEmpty() ?
                Database.registrationsTable.get(Database.registrationsTable.size()-1).getId() : "Empty";
    }
}
