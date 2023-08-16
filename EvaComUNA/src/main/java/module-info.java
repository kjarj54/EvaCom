module cr.ac.una.evacomuna {
    requires javafx.controls;
    requires javafx.fxml;

    opens cr.ac.una.evacomuna to javafx.fxml;
    exports cr.ac.una.evacomuna;
}
