
package com.milton.instituto_descartes.controllers;

import com.milton.instituto_descartes.HelloApplication;
import com.milton.instituto_descartes.models.*;
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

    private Student student = new Student();
    private Instituto instituto = HelloApplication.getAdmin();

    @FXML
    void bttonAdd(MouseEvent event) {
        try {
            student.setNombre(textNombre.getText().trim());
            student.setApellido(textApellido.getText().trim());
            student.setEdad(Integer.parseInt(textEdad.getText().trim()));
            student.setMatricula(textMatricula.getText().trim());
            student.setGenero(bttonGenero.isSelected() ? "Mujer" : "Hombre");

            IBase_Datos baseDatos = null;
            String selectedDatabase = comboBoxB.getValue();
            switch (selectedDatabase) {
                case "MySQL":
                    baseDatos = new MySQL();
                    break;
                case "Oracle":
                    baseDatos = new Oracle();
                    break;
                case "PostgreSQL":
                    baseDatos = new PostgreSQL();
                    break;
                default:
                    showAlert("Error", "Seleccione una base de datos", "Por favor, seleccione una base de datos antes de agregar un estudiante.", Alert.AlertType.ERROR);
                    return;
            }

            if (baseDatos != null) {
                Instituto instituto = new Instituto(baseDatos);
                instituto.saveStudent(student);
                showAlert("Éxito", "Estudiante Agregado", "El estudiante ha sido agregado correctamente.", Alert.AlertType.INFORMATION);
                limpiarCampos();
                System.out.println("Estudiante agregado: " + student.toString());
            } else {
                showAlert("Error", "Base de Datos No Disponible", "No se ha seleccionado una base de datos válida.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Error al ingresar datos", "Ingrese datos válidos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void bttonCancelar(MouseEvent event) {
        HelloApplication.getStageView().close();
    }

    @FXML
    void bttonGenero(MouseEvent event) {
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
}
