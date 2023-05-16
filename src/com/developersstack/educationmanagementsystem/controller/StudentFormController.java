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
import java.util.Optional;

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
    public Button btnSaveAndUpdateStudent;
    private String searchText = "";
    private final DB_Connection dbcon = new DB_Connection();


    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        setStudentID();
        setTableData(searchText);

        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            searchText = newValue.toLowerCase();
            setTableData(searchText);
        }));

        tblStudent.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    if (null != newValue) {
                        setData(newValue);
                    }
        }));
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void addNewStudentOnAction(ActionEvent actionEvent) {
        resetStudentDetailBox();
        btnSaveAndUpdateStudent.setText("Save Student");
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
                    if (btnSaveAndUpdateStudent.getText().equalsIgnoreCase("save student")) {

                        dbcon.addStudent(new Student(id, name, LocalDate.parse(dob), address));
                        new Alert(Alert.AlertType.INFORMATION, "Student Added Successfully!").show();

                    }  else {

                        dbcon.updateStudentDetails(new Student(id, name, LocalDate.parse(dob), address));
                        btnSaveAndUpdateStudent.setText("Save Student");
                        new Alert(Alert.AlertType.INFORMATION, "Student Details Update Successfully!").show();

                    }

                    resetStudentDetailBox();
                    setTableData(searchText);

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

    private void setData(StudentTM tm) {
        txtStudentID.setText(tm.getId());
        txtFullName.setText(tm.getName());
        txtDOB.setValue(LocalDate.parse(tm.getDob()));
        txtAddress.setText(tm.getAddress());
        btnSaveAndUpdateStudent.setText("Update Student");
    }

    private void setTableData(String searchText) {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();

        for (Student st : dbcon.getStudentTable()) {

            if (st.getFullName().toLowerCase().contains(searchText)) {
                Button btn = new Button("Delete");

                obList.add(new StudentTM(st.getId(), st.getFullName(),
                        String.valueOf(st.getDateOfBirth()), st.getAddress(), btn));

                btn.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                        dbcon.deleteStudent(st);
                        setTableData(searchText);
                        new Alert(Alert.AlertType.INFORMATION, "Student Delete Successfully").show();
                    }
                });
            }
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
