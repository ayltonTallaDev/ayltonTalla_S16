module vallegrande.edu.pe.sistema_videojuegos {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens vallegrande.edu.pe.sistema_videojuegos to javafx.fxml;

    opens vallegrande.edu.pe.sistema_videojuegos.controller
            to javafx.fxml;

    opens vallegrande.edu.pe.sistema_videojuegos.model
            to javafx.base;

    exports vallegrande.edu.pe.sistema_videojuegos;
}


