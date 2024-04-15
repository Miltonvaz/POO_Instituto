package com.milton.instituto_descartes.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.milton.instituto_descartes.HelloApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttonReturn;

    @FXML
    private Button bttonStudent;

    @FXML
    private Button bttonBaseDatos;

    @FXML
    void bttonBaseDatos(MouseEvent event) {
        HelloApplication.newStage("basesDeDatos-view","Base de Datos");
    }

    @FXML
    void bttonReturn(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void bttonStudent(MouseEvent event) {
        HelloApplication.newStage("students-view","Agregar Estudiantes");
    }

    @FXML
    void initialize() {

    }
}
