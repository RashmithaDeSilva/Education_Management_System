package com.developersstack.educationmanagementsystem.controller;

import com.developersstack.educationmanagementsystem.dbconnection.DB_Connection;
import com.developersstack.educationmanagementsystem.view.tm.TechPopup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class TechnologiesFormController {
    public AnchorPane contextTechnologies;
    public TableView<TechPopup> tblPopupTechnologies;
    public TableColumn<Object, Object> colPopupID;
    public TableColumn<Object, Object> colPopupTechnologies;
    private final DB_Connection dbcon = new DB_Connection();
    private static String programCode;

    public void initialize() {
        colPopupID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPopupTechnologies.setCellValueFactory(new PropertyValueFactory<>("technologiesName"));
        setDataIntoTable();
    }

    public void setProgramCode(String code) {
        programCode = code;
    }

    private void setDataIntoTable() {
        ObservableList<TechPopup> techPopups = FXCollections.observableArrayList();
        String[] techArray = dbcon.getTechnologies(programCode);
        for (int i=0;i<techArray.length;i++) {
            techPopups.add(new TechPopup(String.valueOf(i+1), techArray[i]));
        }
        tblPopupTechnologies.setItems(techPopups);
    }
}
