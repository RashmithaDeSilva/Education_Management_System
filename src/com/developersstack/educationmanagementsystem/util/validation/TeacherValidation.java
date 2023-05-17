package com.developersstack.educationmanagementsystem.util.validation;

public class TeacherValidation {

    public boolean nameValidation(String name) {
        return name.matches("[A-Za-z]+");
    }

    public boolean addressValidation(String address) {
        return !address.equals("");
    }

    public boolean contactNumberValidation(String contactNumber) {
        // Remove any whitespace characters from the contact number
        contactNumber = contactNumber.replaceAll("\\s+", "");

        // Define the regular expression pattern for a valid contact number
        String pattern = "^\\+?\\d{1,3}?\\d{9}$"; // Allows optional "+" and 1 to 3 digit country code

        // Check if the contact number matches the pattern
        return contactNumber.matches(pattern);
    }
}
