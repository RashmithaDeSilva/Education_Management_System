package com.developersstack.educationmanagementsystem.util.validation;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StudentValidation {

    public boolean nameValidation(String name) {
        return name.matches("[A-Za-z]+");
    }

    public boolean DOB_Validation(String dateOfBirth) {
        try {
            // Parse the input string to LocalDate
            LocalDate dob = LocalDate.parse(dateOfBirth);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Check if the date of birth is in the past and not in the future
            return dob.isBefore(currentDate) || dob.isEqual(currentDate);

        } catch (DateTimeParseException e) {
            // The input string is not a valid date
            return false;
        }
    }

    public boolean addressValidation(String address) {
        return !address.equals("");
    }

    public boolean idValidation(String id) {
        return new DB_Connection().checkStudentID(id);
    }
}
