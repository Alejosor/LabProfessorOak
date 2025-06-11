module com.example.labprofessoroak {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires com.fasterxml.jackson.databind;
    requires java.net.http;

    opens com.example.labprofessoroak to javafx.fxml;
    opens com.example.labprofessoroak.controller to javafx.fxml;
    exports com.example.labprofessoroak;
    exports com.example.labprofessoroak.controller;
}