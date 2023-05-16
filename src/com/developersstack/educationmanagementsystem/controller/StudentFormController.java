package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Student;
import com.developersstack.educationmanagementsystem.util.validation.StudentValidation;
import com.developersstack.educationmanagementsystem.view.tm.StudentTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Objects;

public class StudentFormController {
    public AnchorPane contextStudentForm;
    public TextField txtStudentID;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtSearch;
    public DatePicker txtDOB;
    public TableView<StudentTM> tblStudent;
    public TableColumn<Object, String> colID;
    public TableColumn<Object, String> colName;
    public TableColumn<Object, String> colDOB;
    public TableColumn<Object, String> colAddress;
    public TableColumn<Object, Button> colOption;
    private final DB_Connection dbcon = new DB_Connection();


    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        setStudentID();
        setTableData();
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
    }

    public void saveStudentOnAction(ActionEvent actionEvent) throws ParseException {
        String id = txtStudentID.getText();
        String name = txtFullName.getText();
        String dob = String.valueOf(txtDOB.getValue());
        String address = txtAddress.getText();

        StudentValidation sv = new StudentValidation();

        if (sv.nameValidation(name)) {
            if (sv.DOB_Validation(dob)) {
                if (sv.addressValidation(address)) {

                    dbcon.addStudent(new Student(id, name, LocalDate.parse(dob), address));
                    resetStudentDetailBox();
                    setTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Student Added Successfully!").show();

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

    private void setTableData() {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();
        for (Student st : dbcon.getStudentTable()) {
            obList.add(new StudentTM(st.getId(), st.getFullName(), String.valueOf(st.getDateOfBirth()),
                    st.getAddress(), new Button("Delete")));
        }
        tblStudent.setItems(obList);
    }

    private void setStudentID() {
        if (!dbcon.getLastStudentID().equalsIgnoreCase("empty")) {
            String[] idArray = dbcon.getLastStudentID().split("-");
            String idNumber = "";
            for (int i=1;i<idArray.length;i++) {
                idNumber += idArray[i];
            }
            txtStudentID.setText("S-"+(Integer.parseInt(idNumber)+1));

        } else {
            txtStudentID.setText("S-1");
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

    private void resetStudentDetailBox() {
        setStudentID();
        txtFullName.clear();
        txtDOB.setValue(null);
        txtAddress.clear();
    }
}
