package com.developersstack.educationmanagementsystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;


public class DashboardFormController {
    public AnchorPane contextDashboard;
    public Label lblDate;
    public Label lblTime;


    public void initialize() {
        setDate();
        setTime();
    }

    private void setTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                        e-> lblTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")))),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void setDate() {
        lblDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }

    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        setUI("StudentForm");
    }

    public void intakesOnA(ActionEvent actionEvent) {
    }

    public void programsOnAction(ActionEvent actionEvent) {
    }

    public void teachersOnAction(ActionEvent actionEvent) {
    }

    public void registrationOnAction(ActionEvent actionEvent) {
    }

    public void logoutOnAction(ActionEvent actionEvent) throws IOException {
        setUI("LoginForm");
        new Alert(Alert.AlertType.INFORMATION, "Logout Successfully !").show();
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextDashboard.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
