package com.example.labprofessoroak;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ProfessorOakLab extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Laboratorio del Profesor Oak");

        showIntroScreen();
    }

    private void showIntroScreen() {
        // Layout para la pantalla de introducción
        VBox introLayout = new VBox(20);
        introLayout.setAlignment(Pos.CENTER);
        introLayout.setStyle("-fx-background-color: #E0FFFF; -fx-padding: 20;"); // Color pastel azul claro

        Label titleLabel = new Label("Laboratorio del Profesor Oak");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #5F9EA0;"); // Color pastel verde azulado

        // Carga y muestra la imagen del Profesor Oak
        ImageView oakImageView = new ImageView();
        try {
            Image oakImage = new Image(getClass().getResourceAsStream("/com/example/labprofessoroak/Profesor.png"));
            oakImageView.setImage(oakImage);
            oakImageView.setFitHeight(150); // Ajusta el tamaño según necesites
            oakImageView.setFitWidth(150);
        } catch (Exception e) {
            System.err.println("Error cargando la imagen: " + e.getMessage());
            // placeholder
            Label oakImagePlaceholder = new Label("O\n<>\n/\\");
            oakImagePlaceholder.setStyle("-fx-font-size: 30px;");
            introLayout.getChildren().add(oakImagePlaceholder);
        }

        Label questionLabel = new Label("¿Quieres escoger a tu primer Pokemon?");
        questionLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #778899;"); // Color pastel gris

        Button yesButton = new Button("Si");
        yesButton.setStyle("-fx-background-color: #ADD8E6; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20; -fx-background-radius: 5;"); // Color pastel azul claro
        yesButton.setOnAction(e -> {
            try {
                Parent decisionView = FXMLLoader.load(getClass().getResource("/com/example/labprofessoroak/decision-view.fxml"));
                Scene decisionScene = new Scene(decisionView, 1200, 800);
                primaryStage.setScene(decisionScene);
                primaryStage.setTitle("Árbol de Decisiones Pokémon");
            } catch (Exception ex) {
                System.err.println("Error al cargar la vista de decisiones: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        Button noButton = new Button("No");
        noButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20; -fx-background-radius: 5;"); // Color pastel rosa claro
        noButton.setOnAction(e -> showFarewellScreen());

        // Contenedor HBox para los botones Si y No
        HBox buttonBox = new HBox(10); // Espacio de 10px entre botones
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(yesButton, noButton);

        // Añadir elementos al layout
        introLayout.getChildren().clear(); // Limpiar el placeholder si se agregó
        introLayout.getChildren().addAll(titleLabel);
        if (oakImageView.getImage() != null) {
            introLayout.getChildren().add(oakImageView);
        } else {
            // Si la imagen no se cargó, usar el placeholder
            Label oakImagePlaceholder = new Label("O\n<>\n/\\");
            oakImagePlaceholder.setStyle("-fx-font-size: 30px;");
            introLayout.getChildren().add(oakImagePlaceholder);
        }
        introLayout.getChildren().addAll(questionLabel, buttonBox);

        Scene introScene = new Scene(introLayout, 1200, 800);
        primaryStage.setScene(introScene);
        primaryStage.show();
    }

    private void showFarewellScreen() {
        VBox farewellLayout = new VBox(20);
        farewellLayout.setAlignment(Pos.CENTER);
        farewellLayout.setStyle("-fx-background-color: #F0F8FF; -fx-padding: 20;"); // Color pastel blanco azulado

        Label farewellMessage = new Label("Bueno, cuando estés listo para tu aventura vuelve a visitarme");
        farewellMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: #778899;"); // Color pastel gris

        // Carga y muestra la imagen del Profesor Oak
        ImageView oakImageView = new ImageView();
        try {
            Image oakImage = new Image(getClass().getResourceAsStream("/com/example/labprofessoroak/Profesor.png"));
            oakImageView.setImage(oakImage);
            oakImageView.setFitHeight(150); // Ajusta  tamaño de la imagen
            oakImageView.setFitWidth(150);
        } catch (Exception e) {
            System.err.println("Error cargando la imagen: " + e.getMessage());
            // Fallback en caso de que la imagen no se cargue
            Label oakImagePlaceholder = new Label("O\n<>\n/\\");
            oakImagePlaceholder.setStyle("-fx-font-size: 30px;");
            farewellLayout.getChildren().add(oakImagePlaceholder);
        }

        Button willReturnButton = new Button("Si volveré...");
        willReturnButton.setStyle("-fx-background-color: #DDA0DD; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10 20; -fx-background-radius: 5;"); // Color pastel ciruela
        willReturnButton.setOnAction(e -> {
            System.out.println("El programa se cierra.");
            primaryStage.close(); // Cierra la aplicación
        });

        farewellLayout.getChildren().clear(); // Limpiar el placeholder si se agregó
        farewellLayout.getChildren().addAll(farewellMessage);
        if (oakImageView.getImage() != null) {
            farewellLayout.getChildren().add(oakImageView);
        } else {
            // Si la imagen no se cargó, usar el placeholder
            Label oakImagePlaceholder = new Label("O\n<>\n/\\");
            oakImagePlaceholder.setStyle("-fx-font-size: 30px;");
            farewellLayout.getChildren().add(oakImagePlaceholder);
        }
        farewellLayout.getChildren().add(willReturnButton);

        Scene farewellScene = new Scene(farewellLayout, 1200, 800);
        primaryStage.setScene(farewellScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
} 