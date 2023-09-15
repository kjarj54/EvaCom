module cr.ac.una.evacomuna {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;
    requires com.jfoenix;
    requires jakarta.xml.bind;
    requires jakarta.xml.soap;
    requires jakarta.xml.ws;
    requires java.xml.ws;
   
    
    opens cr.ac.una.evacomuna to javafx.fxml,java.base,com.jfoenix;
    opens cr.ac.una.evacomuna.controller to javafx.fxml;
    opens cr.ac.una.evacomuna.util to javafx.fxml;
    opens cr.ac.una.evacomunaws.controller;
    
    exports cr.ac.una.evacomuna;
    exports cr.ac.una.evacomuna.controller;
    exports cr.ac.una.evacomuna.util;
}
