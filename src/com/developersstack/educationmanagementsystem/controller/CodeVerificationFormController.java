package com.developersstack.educationmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CodeVerificationFormController {
    public AnchorPane contextVerifyForm;
    public Label txtSelectedEmail;
    public TextField txtCode;
    private int verifyCode;


    public void verifyOnAction(ActionEvent actionEvent) throws IOException {
        if (String.valueOf(verifyCode).equals(txtCode.getText())) {
            setUI("");

        } else {
            new Alert(Alert.AlertType.ERROR, "This Code is Incorrect Try Again !").showAndWait();
        }
    }

    public void ChangeEmailOnAction(ActionEvent actionEvent) throws IOException {setUI("ForgotPasswordForm");}

    public void setUserData(int varificationCode, String email) {
        txtSelectedEmail.setText(email);
        verifyCode = varificationCode;
        System.out.println(verifyCode);
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextVerifyForm.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
