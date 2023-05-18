package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Program;
import com.developersstack.educationmanagementsystem.view.tm.TechAddTM;
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

public class ProgramFormController {
    public AnchorPane contextProgramManagement;
    public TextField txtCode;
    public TextField txtName;
    public TextField txtCost;
    public TextField txtTechnologies;
    public ComboBox<String> cmbTeacherID;
    public TextField txtSearch;
    public TableView<TechAddTM> tblTechnologies;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colTechnologies;
    public TableColumn<Object, Object> colRemove;
    public TableView tblProgram;
    public TableColumn<Object, Object> colCode;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colTeachID;
    public TableColumn<Object, Object> colCost;
    public TableColumn<Object, Object> colOption;
    private final DB_Connection dbcon = new DB_Connection();
    private final ObservableList<TechAddTM> techObList = FXCollections.observableArrayList();


    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTechnologies.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("button"));

        setCode();
        setTeacherID();


    }

    public void addNewProgramOnAction(ActionEvent actionEvent) {
    }

    public void bachToHomeOnAction(ActionEvent actionEvent) throws IOException {setUI("DashboardForm");}

    public void saveAndUpdateOnAction(ActionEvent actionEvent) {
        try {
            String code = txtCode.getText();
            String name = txtName.getText().trim();
            double cost = Double.parseDouble(txtCost.getText().trim());
            String teacherID = cmbTeacherID.getSelectionModel().getSelectedItem();
            String[] technologiesArray = new String[techObList.size()];
            for (int i=0;i<technologiesArray.length;i++) {
                technologiesArray[i] = techObList.get(i).getName();
            }

            if (nameValidation(name)) {
                if (!txtCost.getText().equals("")) {
                    if (teacherID != null) {
                        if (techObList.size() >= 1) {

                            dbcon.addProgram(new Program(code, name, technologiesArray, teacherID, cost));
                            resetInputBox();

                        } else {
                            alertError("Technologies Stack Is Empty !", "Add Technologies Correctly",
                                    "Fill Technologies Stack !");
                        }

                    } else {
                        alertError("Teacher ID Incorrect !", "Set Teacher ID Correctly",
                                "Teacher ID Is Incorrect !");
                    }
                }
            } else {
                alertError("Program Name Incorrect !", "Set Program Name Correctly",
                        "Program Name Is Incorrect !");
            }

        } catch (Exception e) {
            alertError("Cost Incorrect !", "Set Cost Correctly",
                    "Cost Is Invalid Value Type !");
        }
    }

    public void txtTechnologiesOnAction(ActionEvent actionEvent) {
        if (!isExists(txtTechnologies.getText().trim())) {
            Button btn = new Button("Remove");
            TechAddTM tm = new TechAddTM((techObList.size()+1), txtTechnologies.getText(), btn);
            techObList.add(tm);
            tblTechnologies.setItems(techObList);
            txtTechnologies.clear();

            btn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                    techObList.remove(tm);
                    new Alert(Alert.AlertType.INFORMATION, "Technologies Remove Successfully").show();
                }
            });

        } else {
            new Alert(Alert.AlertType.WARNING, "This Technologies Already Exist").show();
        }
    }

    private void resetInputBox() {
        setCode();
        txtName.clear();
        txtCost.clear();
        techObList.clear();
    }

    private void alertError(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

    public boolean nameValidation(String name) {return name.matches("[A-Za-z]+");}

    private boolean isExists(String tech) {
        return techObList.stream().anyMatch(e -> e.getName().toLowerCase().equalsIgnoreCase(tech));
    }

    private void setTeacherID() {
        cmbTeacherID.setItems(FXCollections.observableArrayList(dbcon.getTeacherIDsAndNames()));
    }

    private void setCode() {
        if (!dbcon.getLastProgramCode().equalsIgnoreCase("empty")) {
            String[] codeArray = dbcon.getLastProgramCode().split("-");
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
