package com.milton.instituto_descartes.controllers;

import com.milton.instituto_descartes.HelloApplication;
import com.milton.instituto_descartes.models.Escuela;
import com.milton.instituto_descartes.models.Estudiante;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StudentsController {

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private TextField textMatricula;

    @FXML
    private TextField textEdad;

    @FXML
    private Button bttonAdd;

    @FXML
    private Button bttonCancelar;

    @FXML
    private RadioButton bttonGenero;

    @FXML
    private ComboBox<String> comboBoxB;

    private Escuela escuela = HelloApplication.getAdmin();

    @FXML
    void bttonAdd(MouseEvent event) {
        try {
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre(textNombre.getText().trim());
            estudiante.setApellido(textApellido.getText().trim());
            estudiante.setEdad(Integer.parseInt(textEdad.getText().trim()));
            estudiante.setMatricula(textMatricula.getText().trim());
            estudiante.setGenero(bttonGenero.isSelected() ? "Mujer" : "Hombre");
            String selectedDatabase = comboBoxB.getValue();
            if (selectedDatabase == null || selectedDatabase.isEmpty()) {
                showAlert("Error", "Seleccione una base de datos", "Por favor, seleccione una base de datos antes de agregar un estudiante.", Alert.AlertType.ERROR);
                return;
            }
            escuela.save(estudiante);
            showAlert("Éxito", "Estudiante Agregado", "El estudiante ha sido agregado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
            System.out.println("Estudiante agregado: " + estudiante.toString());
        } catch (NumberFormatException e) {
            showAlert("Error", "Error al ingresar datos", "Ingrese datos válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void bttonCancelar(MouseEvent event) {
        HelloApplication.getStageView().close();
    }

    @FXML
    void initialize() {
        comboBoxB.getItems().addAll("MySQL", "Oracle", "PostgreSQL");
    }

    private void showAlert(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        textNombre.clear();
        textApellido.clear();
        textEdad.clear();
        textMatricula.clear();
    }

    public void bttonGenero(MouseEvent mouseEvent) {
    }
}
