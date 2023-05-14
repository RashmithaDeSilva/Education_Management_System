package com.developersstack.educationmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/LoginForm.fxml")));
        Stage stage = (Stage) contextSignupForm.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }

    public void signupOnAction(ActionEvent actionEvent) {
    }
}
