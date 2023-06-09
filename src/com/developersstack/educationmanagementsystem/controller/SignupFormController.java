package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.User;
import com.developersstack.educationmanagementsystem.util.security.PasswordManager;
import com.developersstack.educationmanagementsystem.util.validation.UserValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupFormController {
    public AnchorPane contextSignupForm;
    public TextField txtFirstName;
    public PasswordField txtPassword;
    public TextField txtLastName;
    public TextField txtEmail;
    public PasswordField txtConformPassword;

    // Already have an account on action button
    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
    }

    // Sign up on action button
    public void signupOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().toLowerCase();
        email = email.trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String password = txtPassword.getText().trim();
        String conformPassword = txtConformPassword.getText().trim();

        UserValidation uv = new UserValidation();
        DB_Connection db = new DB_Connection();
        PasswordManager pm = new PasswordManager();

        if (uv.nameValidation(firstName)) {
            if (uv.nameValidation(lastName)) {
                if (uv.emailValidation(email)) {
                    if (uv.passwordValidation(password, conformPassword)) {
                        // String hashPassword = pm.encode(password);
                        db.addUser(new User(firstName, lastName, email, pm.encode(password)));
                        new Alert(Alert.AlertType.INFORMATION, "Welcome!").show();
                        setUI("LoginForm");

                    }else {
                        alertError("Password Incorrect !", "Set Password Correctly",
                                "Your Password and Conform Password is Not the Same !");
                    }

                }else {
                    alertError("Email Incorrect !", "Set Email Correctly",
                            "Your Email is Incorrect !");
                }

            }else {
                alertError("Name Incorrect !", "Set Name Correctly",
                        "Your Last Name is Incorrect !");
            }

        } else {
            alertError("Name Incorrect !", "Set Name Correctly",
                    "Your First Name is Incorrect !");
        }
    }

    // Error alert
    private void alertError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    // Set UI and change stage into another stage
    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextSignupForm.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
