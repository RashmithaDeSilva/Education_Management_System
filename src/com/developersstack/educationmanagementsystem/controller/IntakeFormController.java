package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Intake;
import com.developersstack.educationmanagementsystem.model.Program;
import com.developersstack.educationmanagementsystem.util.validation.IntakeValidation;
import com.developersstack.educationmanagementsystem.view.tm.IntakeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class IntakeFormController {
    public AnchorPane contextIntake;
    public TextField txtIntakeID;
    public TextField txtIntakeName;
    public DatePicker txtStartDate;
    public ComboBox<String> cmbPrograms;
    public TextField txtSearch;
    public Button btnSaveAndUpdateIntake;
    public TableView<IntakeTM> tblIntake;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colStartDate;
    public TableColumn<Object, Object> colProgram;
    public TableColumn<Object, Object> colOption;
    public TableColumn<Object, Object> colComplete;
    private String searchText = "";
    private final DB_Connection dbcon = new DB_Connection();


    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("intakeID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("intakeName"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("programID"));
        colComplete.setCellValueFactory(new PropertyValueFactory<>("intakeCompleteness"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setIntakeCode();
        setProgramData();
        setIntakeDate(searchText);

        txtSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            searchText = newValue.toLowerCase();
            setIntakeDate(searchText);
        }));

        tblIntake.getSelectionModel().selectedItemProperty()
            .addListener(((observable, oldValue, newValue) -> {
                if (null != newValue) {
                    //setData(newValue);
                }
        }));
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
                        setIntakeDate(searchText);

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

    private void setIntakeDate(String searchText) {
        ObservableList<IntakeTM> obList = FXCollections.observableArrayList();

        for (Intake i : dbcon.getIntakeTable()) {
           if (i.getIntakeName().toLowerCase().contains(searchText)) {

               Button btn = new Button("Delete");
               obList.add(new IntakeTM(i.getIntakeID(), String.valueOf(i.getStartDate()), i.getIntakeName(),
                       i.getProgramID(), i.isIntakeCompleteness(), btn));

               btn.setOnAction(e-> {
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                           ButtonType.YES, ButtonType.NO);
                   Optional<ButtonType> buttonType = alert.showAndWait();
                   if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                       dbcon.deleteIntake(i);
                       setIntakeDate(searchText);
                       new Alert(Alert.AlertType.INFORMATION, "Intake Delete Successfully").show();
                   }
               });
               btn.setCursor(Cursor.HAND);
           }
        }
        tblIntake.setItems(obList);
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
            String[] intakeCodeArray = dbcon.getlastIntakeCode().split("-");
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
