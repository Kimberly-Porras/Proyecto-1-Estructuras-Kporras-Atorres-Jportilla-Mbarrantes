module controller.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens controller.proyecto to javafx.fxml;
    exports controller.proyecto;
}
