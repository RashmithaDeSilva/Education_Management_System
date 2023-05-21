package com.developersstack.educationmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class IntakeFormController {
    public AnchorPane contextIntake;
    public TextField txtIntakeID;
    public TextField txtIntakeName;
    public DatePicker txtStartDate;
    public ComboBox cmbPrograms;
    public TextField txtSearch;
    public Button btnSaveAndUpdateIntake;
    public TableView tblIntake;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colStartDate;
    public TableColumn colProgram;
    public TableColumn colOption;

    public void addNewIntakeOnAction(ActionEvent actionEvent) {
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void saveAndUpdateOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextIntake.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
