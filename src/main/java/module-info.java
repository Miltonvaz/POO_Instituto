module com.milton.instituto_descartes {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    opens com.milton.instituto_descartes.controllers to javafx.fxml;
    opens com.milton.instituto_descartes.models to javafx.base;
    exports com.milton.instituto_descartes;
    opens com.milton.instituto_descartes to javafx.fxml;
    exports com.milton.instituto_descartes.controllers;
}