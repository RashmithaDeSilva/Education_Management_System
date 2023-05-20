package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Teacher;
import com.developersstack.educationmanagementsystem.util.validation.TeacherValidation;
import com.developersstack.educationmanagementsystem.view.tm.TeacherTM;
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
import java.util.Objects;
import java.util.Optional;

public class TeacherFormController {
    public AnchorPane contextTeacherManagement;
    public Button btnSaveAndUpdateTeacher;
    public TextField txtCode;
    public TextField txtName;
    public TextField txtContactNubmer;
    public TextField txtAddress;
    public TextField txtSearch;
    public TableView<TeacherTM> tblTeacher;
    public TableColumn<Object, String> colCode;
    public TableColumn<Object, String> colName;
    public TableColumn<Object, String> colContactNumber;
    public TableColumn<Object, String> colAddress;
    public TableColumn<Object, Button> colOption;
    private String searchText = "";
    private final DB_Connection dbcon = new DB_Connection();


    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

        setCode();
        setDataIntoTable(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue.toLowerCase();
            setDataIntoTable(searchText);
        });

        tblTeacher.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (null != newValue) {
                        setData(newValue);
                    }
        });

    }

    public void addNewTeacherOnAction(ActionEvent actionEvent) {
        resetInputBox();
        btnSaveAndUpdateTeacher.setText("Save Teacher");
    }

    public void saveAndUpdateTeacherOnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String name = txtName.getText();
        String contactNumber = txtContactNubmer.getText();
        String address = txtAddress.getText();

        TeacherValidation tv = new TeacherValidation();

        if  (tv.nameValidation(name)) {
            if (tv.contactNumberValidation(contactNumber)) {
                if (tv.addressValidation(address)) {
                    if (btnSaveAndUpdateTeacher.getText().equalsIgnoreCase("save teacher")) {
                        dbcon.addTeacher(new Teacher(code, name, contactNumber, address));
                        new Alert(Alert.AlertType.INFORMATION, "Teacher Added Successfully!").show();

                    } else {
                        dbcon.updateTeacherDetails(new Teacher(code, name, contactNumber, address));
                        btnSaveAndUpdateTeacher.setText("Save Teacher");
                        new Alert(Alert.AlertType.INFORMATION, "Teacher Details Update Successfully!").show();
                    }

                    resetInputBox();
                    setDataIntoTable(searchText);

                } else {
                    alertError("Address Incorrect !", "Set Address Correctly",
                            "Teacher Address is Incorrect !");
                }

            }else {
                alertError("Contact Number Incorrect !", "Set Contact Number Correctly",
                        "Teacher Contact Number is Incorrect !");
            }

        } else {
            alertError("Name Incorrect !", "Set Name Correctly",
                    "Teacher Name is Incorrect !");
        }
    }

    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    private void setData(TeacherTM tm) {
        txtCode.setText(tm.getCode());
        txtName.setText(tm.getName());
        txtContactNubmer.setText(tm.getContactNumber());
        txtAddress.setText(tm.getAddress());
        btnSaveAndUpdateTeacher.setText("Update Teacher");
    }

    private void setDataIntoTable(String searchText) {
        ObservableList<TeacherTM> obList = FXCollections.observableArrayList();

        for (Teacher t : dbcon.getTeacherTable()) {
            if (t.getName().toLowerCase().contains(searchText)) {

                Button btn = new Button("Delete");
                obList.add(new TeacherTM(t.getCode(), t.getName(), t.getContactNumber(), t.getAddress(), btn));

                btn.setOnAction(e->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                        dbcon.deleteTeacher(t);
                        setDataIntoTable(searchText);
                        new Alert(Alert.AlertType.INFORMATION, "Teacher Delete Successfully").show();
                    }
                });
            }
        }

        tblTeacher.setItems(obList);
    }

    private void resetInputBox() {
        setCode();
        txtName.clear();
        txtContactNubmer.clear();
        txtAddress.clear();
    }

    private void setUI(String UI_Name) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/" + UI_Name + ".fxml")));
        Stage stage = (Stage) contextTeacherManagement.getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }

    private void setCode() {
        if (!dbcon.getLastTeacherCode().equalsIgnoreCase("empty")) {
            String[] codeArray = dbcon.getLastTeacherCode().split("-");
            StringBuilder codeNumber = new StringBuilder();
            for (int i=1;i<codeArray.length;i++) {
                codeNumber.append(codeArray[i]);
            }
            txtCode.setText("T-" + (Integer.parseInt(codeNumber.toString())+1));

        } else {
            txtCode.setText("T-1");
        }
    }

    private void alertError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}
