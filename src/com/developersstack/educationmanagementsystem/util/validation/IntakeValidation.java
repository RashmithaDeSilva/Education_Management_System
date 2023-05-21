package com.developersstack.educationmanagementsystem.util.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class IntakeValidation {

    public boolean nameValidation(String name) {return name.matches("[A-Za-z]+");}

    public boolean startDateValidation(String startDate) {
        try {
            // Parse the input string to LocalDate
            LocalDate start = LocalDate.parse(startDate);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            return  start.isEqual(currentDate) || start.isAfter(currentDate);

        } catch (DateTimeParseException e) {
            // The input string is not a valid date
            return false;
        }
    }

}
