package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
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

public class LoginFormController {
    public AnchorPane contextLoginForm;
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void createAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignupForm");
    }

    public void forgotPasswordOnAction(ActionEvent actionEvent) {
    }

    public void loginOnAction(ActionEvent actionEvent) {
        DB_Connection db = new DB_Connection();

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (db.checkUserLogingInfomation(email, password)) {
            //new Alert(Alert.AlertType.CONFIRMATION).show();
            System.out.println("Success !");

        } else {
            new Alert(Alert.AlertType.ERROR, "Wrong email or password").show();
        }
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextLoginForm.getScene().getWindow();
        stage.setScene(new Scene(parent));
    }
}
