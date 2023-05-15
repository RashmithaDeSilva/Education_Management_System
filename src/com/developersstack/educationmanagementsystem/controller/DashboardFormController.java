package com.developersstack.educationmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class DashboardFormController {
    public AnchorPane contextDashboard;
    public Label ldlDate;
    public Label ldlTime;

    public void initialize() {
        setDate();
    }

    private void setDate() {
        Date date = new Date();
        System.out.println(date);
    }

    public void studentOnAction(ActionEvent actionEvent) {
    }

    public void intakesOnA(ActionEvent actionEvent) {
    }

    public void programsOnAction(ActionEvent actionEvent) {
    }

    public void teachersOnAction(ActionEvent actionEvent) {
    }

    public void registrationOnAction(ActionEvent actionEvent) {
    }

    public void logoutOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextDashboard.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
