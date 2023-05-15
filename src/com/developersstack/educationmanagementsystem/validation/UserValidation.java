package com.developersstack.educationmanagementsystem.validation;

public class UserValidation {

    public boolean nameValidation(String name) {
        return name.matches("[A-Za-z]+");
    }

    public boolean passwordValidation(String password, String conformPassword) {
        return password.equals(conformPassword);
    }

    public boolean emailValidation(String email) {
        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
    }
}
