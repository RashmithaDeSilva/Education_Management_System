package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.model.Program;
import com.developersstack.educationmanagementsystem.view.tm.ProgramTM;
import com.developersstack.educationmanagementsystem.view.tm.TechAddTM;
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
    public TableView<ProgramTM> tblProgram;
    public TableColumn<Object, Object> colCode;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colTeachID;
    public TableColumn<Object, Object> colCost;
    public TableColumn<Object, Object> colOption;
    public TableColumn<Object, Object> colProgramTech;
    public Button btnSaveAndUpdate;
    private String searchText = "";
    private final DB_Connection dbcon = new DB_Connection();
    private final ObservableList<TechAddTM> techObList = FXCollections.observableArrayList();


    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTechnologies.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("button"));

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeachID.setCellValueFactory(new PropertyValueFactory<>("teacherID"));
        colProgramTech.setCellValueFactory(new PropertyValueFactory<>("technologies"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setCode();
        setTeacherID();
        setDataIntoTable(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue.toLowerCase();
            setDataIntoTable(searchText);
        });

        tblProgram.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (null != newValue) {
                        setData(newValue);
                    }
        });
    }

    public void addNewProgramOnAction(ActionEvent actionEvent) {

    }

    public void bachToHomeOnAction(ActionEvent actionEvent) throws IOException {setUI("DashboardForm");}

    public void saveAndUpdateOnAction(ActionEvent actionEvent) {
        try {
            String code = txtCode.getText();
            String name = txtName.getText().trim();
            double cost = Double.parseDouble(txtCost.getText().trim());
            String teacherID = cmbTeacherID.getSelectionModel().getSelectedItem().split("\\.")[0];
            String[] technologiesArray = new String[techObList.size()];
            for (int i=0;i<technologiesArray.length;i++) {
                technologiesArray[i] = techObList.get(i).getName();
            }

            if (nameValidation(name)) {
                if (!txtCost.getText().equals("")) {
                    if (teacherID != null) {
                        if (techObList.size() >= 1) {
                            if (btnSaveAndUpdate.getText().equalsIgnoreCase("save program")) {

                                dbcon.addProgram(new Program(code, name, technologiesArray, teacherID, cost));
                                new Alert(Alert.AlertType.INFORMATION, "Successfully Added Program !").show();

                            } else {

                                dbcon.updateProgram(new Program(code, name, technologiesArray, teacherID, cost));
                                new Alert(Alert.AlertType.INFORMATION, "Successfully Update Program !").show();
                            }

                            resetInputBox();
                            setDataIntoTable(searchText);

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

    private void setDataIntoTable(String searchText) {
        ObservableList<ProgramTM> programObList = FXCollections.observableArrayList();

        for (Program p : dbcon.getProgramTable()) {
            if (p.getName().toLowerCase().contains(searchText)) {

                Button btnDelete = new Button("Delete");
                Button btnShow = new Button("Show");

                programObList.add(new ProgramTM(p.getCode(), p.getName(), p.getTeacherID(),
                        p.getCost(), btnShow, btnDelete));

                btnDelete.setOnAction(e-> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                        dbcon.deleteProgram(p);
                        setDataIntoTable(searchText);
                        new Alert(Alert.AlertType.INFORMATION, "Program Delete Successfully").show();
                    }
                });

                btnShow.setOnAction(e-> {
                    try {
                        new TechnologiesFormController().setProgramCode(p.getCode());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(FXMLLoader.load(Objects
                                .requireNonNull(getClass().getResource("../view/TechnologiesForm.fxml")))));
                        stage.show();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                btnDelete.setCursor(Cursor.HAND);
                btnShow.setCursor(Cursor.HAND);
            }
        }
        tblProgram.setItems(programObList);
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
            btn.setCursor(Cursor.HAND);

        } else {
            new Alert(Alert.AlertType.WARNING, "This Technologies Already Exist").show();
        }
    }

    private void setData(ProgramTM tm) {

        techObList.clear();

        for (int i=0;i<dbcon.getTechnologies(tm.getCode()).length;i++) {

            Button btn = new Button("Remove");
            TechAddTM addTM = new TechAddTM((i+1), dbcon.getTechnologies(tm.getCode())[i], btn);
            techObList.add(addTM);

            btn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.isPresent() && buttonType.get().equals(ButtonType.YES)) {
                    techObList.remove(addTM);
                    new Alert(Alert.AlertType.INFORMATION, "Technologies Remove Successfully").show();
                }
            });
            btn.setCursor(Cursor.HAND);
        }

        txtCode.setText(tm.getCode());
        txtName.setText(tm.getName());
        txtCost.setText(String.valueOf(tm.getCost()));
        cmbTeacherID.setValue(tm.getTeacherID());
        tblTechnologies.setItems(techObList);
        btnSaveAndUpdate.setText("Update Program");
    }

    private void resetInputBox() {
        setCode();
        txtName.clear();
        txtCost.clear();
        cmbTeacherID.setValue(null);
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
