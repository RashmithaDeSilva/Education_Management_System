package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Intake;
import com.developersstack.educationmanagementsystem.model.Program;
import com.developersstack.educationmanagementsystem.util.validation.IntakeValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class IntakeFormController {
    public AnchorPane contextIntake;
    public TextField txtIntakeID;
    public TextField txtIntakeName;
    public DatePicker txtStartDate;
    public ComboBox<String> cmbPrograms;
    public TextField txtSearch;
    public Button btnSaveAndUpdateIntake;
    public TableView tblIntake;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colStartDate;
    public TableColumn colProgram;
    public TableColumn colOption;
    public TableColumn colComplete;
    private DB_Connection dbcon = new DB_Connection();


    public void initialize() {
        setIntakeCode();
        setProgramData();
    }

    public void addNewIntakeOnAction(ActionEvent actionEvent) {
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void saveAndUpdateOnAction(ActionEvent actionEvent) {
        try {
            String code = txtIntakeID.getText();
            String name = txtIntakeName.getText();
            LocalDate date = txtStartDate.getValue();
            String programID = cmbPrograms.getSelectionModel().getSelectedItem().split("\\.")[0];

            IntakeValidation iv = new IntakeValidation();

            if (iv.nameValidation(name)) {
                if (iv.startDateValidation(String.valueOf(date))) {
                    if (!programID.equals(cmbPrograms.getPromptText())) {

                        dbcon.addIntake(new Intake(code, date, name, programID, false));
                        resetInputBox();

                    } else {
                        alertError("Program ID Incorrect !", "Select Program ID Correctly",
                                "Selected Program ID Is Incorrect !");
                    }

                } else {
                    alertError("Start Date Incorrect !", "Set Start Date Correctly",
                            "Start Date is Incorrect !");
                }

            } else {
                alertError("Name Incorrect !", "Set Name Correctly",
                        "Intake Name is Incorrect !");
            }

        } catch (Exception e) {
            alertError("Program ID Incorrect !", "Select Program ID Correctly",
                    "Selected Program ID Is Incorrect !");
        }
    }

    private void alertError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    private void resetInputBox() {
        setIntakeCode();
        txtIntakeName.clear();
        txtStartDate.setValue(null);
        cmbPrograms.setValue(null);
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

    private void setProgramData() {
        ObservableList<String> programNamesAndIDs = FXCollections.observableArrayList();
        for (Program p : dbcon.getProgramTable()) {
            programNamesAndIDs.add(p.getCode() + ". " + p.getName());
        }
        cmbPrograms.setItems(programNamesAndIDs);
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextIntake.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
