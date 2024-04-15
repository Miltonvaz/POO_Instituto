
package com.milton.instituto_descartes.controllers;

import com.milton.instituto_descartes.HelloApplication;
import com.milton.instituto_descartes.models.Instituto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private TextField textFieldUsuario;

    @FXML
    private PasswordField textFieldPwd;

    @FXML
    void bttonIniciar(MouseEvent event) {
        String usuario = textFieldUsuario.getText();
        String contraseña = textFieldPwd.getText();
        Instituto admin = HelloApplication.getAdmin();
        if (usuario.equals(admin.getUser()) && contraseña.equals(admin.getPassword())) {
            HelloApplication.newStage("menu-view", "Menú Principal");
        } else {
            mostrarAlerta("Error", "No se pudo iniciar sesión", "Usuario o contraseña incorrecta", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void bttonSalir(MouseEvent mouseEvent) {
        Platform.exit();
    }

    private void mostrarAlerta(String titulo, String encabezado, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
    }
}
