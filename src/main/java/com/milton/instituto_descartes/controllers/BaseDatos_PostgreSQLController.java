    package com.milton.instituto_descartes.controllers;

    import java.net.URL;
    import java.util.ResourceBundle;

    import com.milton.instituto_descartes.HelloApplication;
    import com.milton.instituto_descartes.models.*;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.control.cell.TextFieldTableCell;
    import javafx.scene.input.MouseEvent;
    import javafx.util.converter.IntegerStringConverter;

    public class BaseDatos_PostgreSQLController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button bttonDelete;

        @FXML
        private Button bttonCancelar;

        @FXML
        private Button bttonMostrar;

        @FXML
        private ComboBox<String> comboBoxB;

        @FXML
        private TableView<Student> tableView;

        @FXML
        private TableColumn<Student, String> c1Table;

        @FXML
        private TableColumn<Student, String> c2Table;

        @FXML
        private TableColumn<Student, String> c3Table;

        @FXML
        private TableColumn<Student, Integer> c4Table;

        @FXML
        private TableColumn<Student, String> c5Table;

        @FXML
        private TextField textFieldDelete;

        private Instituto baseDatos;

        private ObservableList<Student> studentList = FXCollections.observableArrayList();

        @FXML
        public void bttonDelate(MouseEvent mouseEvent) {
            Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                boolean eliminado = baseDatos.deleteStudent(selectedStudent);
                if (eliminado) {
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
            baseDatos = new Instituto(new MySQL());
            Instituto oracleInstituto = new Instituto(new Oracle());
            Instituto postgresqlInstituto = new Instituto(new PostgreSQL());
            mostrarEstudiantes(baseDatos, oracleInstituto, postgresqlInstituto);
        }

        private void mostrarEstudiantes(Instituto... institutos) {
            studentList.clear();
            for (Instituto instituto : institutos) {
                if (instituto != null && instituto.getBaseDatos() != null) {
                    studentList.addAll(instituto.getBaseDatos().getStudents());
                }
            }
            tableView.setItems(studentList);
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
                Student student = event.getRowValue();
                student.setNombre(event.getNewValue());
                actualizarEstudiante(student);
            });

            c3Table.setCellFactory(TextFieldTableCell.forTableColumn());
            c3Table.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setApellido(event.getNewValue());
                actualizarEstudiante(student);
            });

            c4Table.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            c4Table.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setEdad(event.getNewValue());
                actualizarEstudiante(student);
            });

            c5Table.setCellFactory(TextFieldTableCell.forTableColumn());
            c5Table.setOnEditCommit(event -> {
                Student student = event.getRowValue();
                student.setGenero(event.getNewValue());
                actualizarEstudiante(student);
            });

        }

        private void actualizarEstudiante(Student student) {
            if (baseDatos != null) {
                baseDatos.updateStudent(student);
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
