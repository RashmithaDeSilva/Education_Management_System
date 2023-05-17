package com.developersstack.educationmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ForgotPasswordFormController {
    public AnchorPane contextForgotPassword;
    public TextField txtEmail;

    public void sendVerificationOnAction(ActionEvent actionEvent) {
    }

    public void backToLoginPageOnAction(ActionEvent actionEvent) throws IOException {setUI("LoginForm");}

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextForgotPassword.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
