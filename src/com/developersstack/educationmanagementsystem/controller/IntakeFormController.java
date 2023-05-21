package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
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
    private DB_Connection dbcon = new DB_Connection();


    public void initialize() {
        setIntakeCode();
    }

    public void addNewIntakeOnAction(ActionEvent actionEvent) {
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void saveAndUpdateOnAction(ActionEvent actionEvent) {
    }

    private void setIntakeCode() {
        if (!dbcon.getlastIntakeCode().equalsIgnoreCase("empty")) {
            String[] intakeCodeArray = txtIntakeID.getText().split("-");
            StringBuilder intakeNumber = new StringBuilder();
            for (int i=1;i<intakeCodeArray.length;i++) {
                intakeNumber.append(intakeCodeArray[i]);
            }
            txtIntakeID.setText("I-" + (Integer.parseInt(String.valueOf(intakeNumber)) + 1));

        } else {
            txtIntakeID.setText("I-1");
        }
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextIntake.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
