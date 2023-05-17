package com.developersstack.educationmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TeacherFormController {
    public AnchorPane contextTeacherManagement;
    public Button btnSaveAndUpdateTeacher;
    public TextField txtCode;
    public TextField txtName;
    public TextField txtContactNubmer;
    public TextField txtAddress;
    public TextField txtSearch;
    public TableView tblTeacher;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colContactNumber;
    public TableColumn colAddress;
    public TableColumn colOption;

    public void addNewTeacherOnAction(ActionEvent actionEvent) {
    }

    public void saveAndUpdateTeacherOnAction(ActionEvent actionEvent) {
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextTeacherManagement.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
