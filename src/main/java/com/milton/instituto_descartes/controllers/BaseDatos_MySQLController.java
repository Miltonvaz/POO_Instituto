package com.milton.instituto_descartes.controllers;

import com.milton.instituto_descartes.HelloApplication;
import com.milton.instituto_descartes.models.Escuela;
import com.milton.instituto_descartes.models.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;

public class BaseDatos_MySQLController {

    @FXML
    private Button bttonDelete;

    @FXML
    private Button bttonCancelar;

    @FXML
    private Button bttonMostrar;

    @FXML
    private TableView<Estudiante> tableView;

    @FXML
    private TableColumn<Estudiante, String> c1Table;

    @FXML
    private TableColumn<Estudiante, String> c2Table;

    @FXML
    private TableColumn<Estudiante, String> c3Table;

    @FXML
    private TableColumn<Estudiante, Integer> c4Table;

    @FXML
    private TableColumn<Estudiante, String> c5Table;

    @FXML
    private TextField textFielModi;

    private Escuela baseDatos = HelloApplication.getAdmin();


    @FXML
    public void bttonDelate(MouseEvent mouseEvent) {
       Estudiante selectedStudent = tableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean deleted = baseDatos.delete(selectedStudent);
            if (deleted) {
                showAlert("Éxito", "Estudiante eliminado", "El estudiante se eliminó correctamente.", Alert.AlertType.INFORMATION);
                mostrarEstudiantes();
            } else {
                showAlert("Error", "No se encontró el estudiante", "No se encontró un estudiante con la matrícula proporcionada.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "Seleccionar Estudiante", "Por favor, seleccione un estudiante de la tabla.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void bttonMostrar(MouseEvent event) {
        mostrarEstudiantes();
    }

    private void mostrarEstudiantes() {
        if (baseDatos != null && baseDatos.getMySQL() != null) {
            ArrayList<Estudiante> estudianteArrayList = baseDatos.getMySQL().getListStudents1();
            ObservableList<Estudiante> estudianteObservableList = FXCollections.observableArrayList(estudianteArrayList);
            tableView.setItems(estudianteObservableList);
        }
    }

    @FXML
    public void bttonCancelar(MouseEvent mouseEvent) {
        HelloApplication.getStageView().close();
    }

    @FXML
    void initialize() {
        c1Table.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        c2Table.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        c3Table.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        c4Table.setCellValueFactory(new PropertyValueFactory<>("edad"));
        c5Table.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tableView.setEditable(true);
        c2Table.setCellFactory(TextFieldTableCell.forTableColumn());
        c2Table.setOnEditCommit(event -> {
            Estudiante estudiante = event.getRowValue();
            estudiante.setNombre(event.getNewValue());
            actualizarEstudiante(estudiante);
        });

        c3Table.setCellFactory(TextFieldTableCell.forTableColumn());
        c3Table.setOnEditCommit(event -> {
            Estudiante estudiante = event.getRowValue();
            estudiante.setApellido(event.getNewValue());
            actualizarEstudiante(estudiante);
        });

        c4Table.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        c4Table.setOnEditCommit(event -> {
            Estudiante estudiante = event.getRowValue();
            estudiante.setEdad(event.getNewValue());
            actualizarEstudiante(estudiante);
        });

        c5Table.setCellFactory(TextFieldTableCell.forTableColumn());
        c5Table.setOnEditCommit(event -> {
            Estudiante estudiante = event.getRowValue();
            estudiante.setGenero(event.getNewValue());
            actualizarEstudiante(estudiante);
        });
    }

    private void actualizarEstudiante(Estudiante estudiante) {
        if (baseDatos != null) {
            baseDatos.updateStudent(estudiante);
            mostrarEstudiantes();
        } else {
            showAlert("Error", "Base de Datos No Disponible", "No se ha seleccionado una base de datos válida.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}
