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

public class LoginFormController {
    public AnchorPane contextLoginForm;
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("../view/SignupForm.fxml");
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) {
    }

    public void loginOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(location)));
        Stage stage = (Stage) contextLoginForm.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
