package com.developersstack.educationmanagementsystem.util.validation;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import java.time.Period;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StudentValidation {

    public boolean nameValidation(String name) {
        return name.matches("[A-Za-z]+");
    }

    public boolean DOB_Validation(String dateOfBirth) {
        int MIN_AGE = 10;
        int MAX_AGE = 70;

        try {
            // Parse the input string to LocalDate
            LocalDate dob = LocalDate.parse(dateOfBirth);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Calculate the age based on the date of birth
            Period age = Period.between(dob, currentDate);

            // Check if the age falls within the specified range
            return age.getYears() >= MIN_AGE && age.getYears() <= MAX_AGE;

        } catch (DateTimeParseException e) {
            // The input string is not a valid date
            return false;
        }
    }

    public boolean addressValidation(String address) {
        return !address.equals("");
    }

}
