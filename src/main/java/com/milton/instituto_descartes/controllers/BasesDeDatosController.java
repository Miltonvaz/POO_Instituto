package com.milton.instituto_descartes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.milton.instituto_descartes.HelloApplication;
import com.milton.instituto_descartes.models.IBase_Datos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class BasesDeDatosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBoxB;

    @FXML
    private Button bttonAdd;

    @FXML
    private Button bttonVolver;

    @FXML
    void bttonAdd(MouseEvent event) {
        IBase_Datos baseDatos = null;
        switch (comboBoxB.getValue()){
            case "MySQL":
                HelloApplication.newStage("baseDatos_MySQL-view","Base De Datos MySQL");
                break;
            case "Oracle":
                HelloApplication.newStage("baseDatos_Oracle-view","Base De Datos Oracle");
                break;
            case "PostgreSQL":
                HelloApplication.newStage("baseDatos_PostgreSQL-view","Base De Datos PostgreSQL");
                break;
        }
    }

    @FXML
    void bttonVolver(MouseEvent event) {
        HelloApplication.getStageView().close();
    }

    @FXML
    void initialize() {
        comboBoxB.getItems().addAll("MySQL", "Oracle", "PostgreSQL");
    }
}
