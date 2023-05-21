package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Program;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistrationFormController {
    public AnchorPane contextRegistration;
    public TextField txtID;
    public TextField txtName;
    public ComboBox<String> cmbProgram;
    public ToggleGroup paidOrNot;
    private final DB_Connection dbcon = new DB_Connection();


    public void initialize() {
        setRegistrationID();
        setPrograms();
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {setUI("DashboardForm");}

    public void registrationOnAction(ActionEvent actionEvent) {
    }

    private void setStudentNames() {
            
    }

    private void setPrograms() {
        ObservableList<String> programList = FXCollections.observableArrayList();
        for (Program p : dbcon.getProgramTable()) {
            programList.add(p.getCode() + ". " + p.getName());
        }
        cmbProgram.setItems(programList);
    }

    private void setRegistrationID() {
        if (!dbcon.getLastRegistrationID().equalsIgnoreCase("empty")) {
            String[] regiArray = dbcon.getLastRegistrationID().split("-");
            StringBuilder regiNumber = new StringBuilder();
            for (int i=1;i<regiArray.length;i++) {
                regiNumber.append(regiArray[i]);
            }
            txtID.setText("R-" + (Integer.parseInt(String.valueOf(regiNumber)) + 1));

        } else {
            txtID.setText("R-1");
        }
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextRegistration.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
