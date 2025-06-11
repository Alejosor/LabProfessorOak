package com.example.labprofessoroak.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DecisionController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}