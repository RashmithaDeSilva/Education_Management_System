package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProgramFormController {
    public AnchorPane contextProgramManagement;
    public TextField txtCode;
    public TextField txtName;
    public TextField txtCost;
    public TextField txtTechnologies;
    public ComboBox cmbTeacherID;
    public TextField txtSearch;
    public TableView tblTechnologies;
    public TableColumn colID;
    public TableColumn colTechnologies;
    public TableColumn colRemove;
    public TableView tblProgram;
    public TableColumn colCode;
    public TableColumn colName;
    public TableColumn colTeachID;
    public TableColumn col;
    public TableColumn colCost;
    public TableColumn colOption;
    private final DB_Connection dbcon = new DB_Connection();


    public void initialize() {
        setCode();
    }

    public void addNewProgramOnAction(ActionEvent actionEvent) {
    }

    public void bachToHomeOnAction(ActionEvent actionEvent) throws IOException {setUI("DashboardForm");}

    public void saveAndUpdateOnAction(ActionEvent actionEvent) {
    }

    private void setCode() {
        if (!txtCode.getText().equals("")) {
            String[] codeArray = txtCode.getText().split("-");
            StringBuilder codeNumber = new StringBuilder();
            for (int i=1;i<codeArray.length;i++) {
                codeNumber.append(codeArray[i]);
            }
            txtCode.setText("P-" + (Integer.parseInt(codeNumber.toString())+1));

        } else {
            txtCode.setText("P-1");
        }
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextProgramManagement.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }
}
