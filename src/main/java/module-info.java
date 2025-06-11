module com.example.labprofessoroak {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.labprofessoroak to javafx.fxml;
    exports com.example.labprofessoroak;
    exports com.example.labprofessoroak.controller;
    opens com.example.labprofessoroak.controller to javafx.fxml;
}