package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ResetPasswordFormController {
    public AnchorPane contextResetPassword;
    public TextField txtNewPassword;
    public TextField txtConformPassword;
    private String email;
    private final DB_Connection dbcon = new DB_Connection();


    public void changePasswordOnAction(ActionEvent actionEvent) throws IOException {
        if (!dbcon.checkUserLoginInformation(email, txtNewPassword.getText())) {
            if (txtNewPassword.getText().equals(txtConformPassword.getText())) {

                dbcon.changePassword(email, txtNewPassword.getText());
                setUI("LoginForm");
                new Alert(Alert.AlertType.INFORMATION, "Successfully Change Your Password !").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again Password  Is Incorrect !").show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "You Can't Set Previse Password Again!").show();
        }
        resetPasswordBox();
    }

    public void setUserData(String email) {
        this.email = email;
    }

    private void resetPasswordBox() {
        txtNewPassword.clear();
        txtConformPassword.clear();
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextResetPassword.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
