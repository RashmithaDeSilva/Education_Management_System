package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.model.Student;
import com.developersstack.educationmanagementsystem.util.validation.StudentValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class StudentFormController {
    public AnchorPane contextStudentForm;
    public TextField txtStudentID;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtSearch;
    public DatePicker txtDOB;

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) {
        String id = txtStudentID.getText();
        String name = txtFullName.getText();
        String dob = String.valueOf(txtDOB.getValue());
        String address = txtAddress.getText();

        StudentValidation sv = new StudentValidation();

        //System.out.println(new Date(txtDOB.getValue().toEpochDay()));
        if (sv.nameValidation(name)) {
            if (sv.DOB_Validation(dob)) {
                if (sv.addressValidation(address)) {
                    Student student = new Student(id, name, new Date(txtDOB.getValue().toEpochDay()), address);
                    new Alert(Alert.AlertType.INFORMATION, "Student Added Successfully!").show();
                    clearStudentDetailBox();

                } else {
                    alertError("Address Incorrect !", "Set Address Correctly",
                            "Student Address is Incorrect !");
                }

            } else {
                alertError("Date of Birth Incorrect !", "Set Date of Birth Correctly",
                        "Student Date of Birth is Incorrect !");
            }

        } else {
            alertError("Name Incorrect !", "Set Name Correctly",
                    "Student Name is Incorrect !");
        }


    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextStudentForm.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }

    private void alertError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    private void clearStudentDetailBox() {
        txtStudentID.clear();
        txtFullName.clear();
        txtDOB.setValue(null);
        txtAddress.clear();
    }
}
