package com.example.labprofessoroak.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class DecisionController {

    @FXML
    private Label questionLabel;

    @FXML
    private VBox optionsContainer;

    private Map<String, JsonNode> questionsMap;
    private String currentQuestionId;

    @FXML
    public void initialize() {
        System.out.println("Inicializando DecisionController...");
        if (questionLabel == null) {
            System.err.println("¡Error! questionLabel es null");
            return;
        }
        if (optionsContainer == null) {
            System.err.println("¡Error! optionsContainer es null");
            return;
        }
        
        loadQuestionsFromJson();
        if (questionsMap == null || questionsMap.isEmpty()) {
            System.err.println("¡Error! No se pudieron cargar las preguntas");
            return;
        }
        
        currentQuestionId = "q1";
        JsonNode firstQuestion = questionsMap.get(currentQuestionId);
        if (firstQuestion == null) {
            System.err.println("¡Error! No se encontró la primera pregunta (q1)");
            return;
        }
        
        System.out.println("Mostrando primera pregunta: " + firstQuestion.get("questionText").asText());
        displayQuestion(firstQuestion);
    }

    private void loadQuestionsFromJson() {
        System.out.println("Intentando cargar questions.json...");
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/com/example/labprofessoroak/questions.json")) {
            if (is == null) {
                System.err.println("No se pudo encontrar el archivo questions.json");
                return;
            }
            JsonNode rootNode = mapper.readTree(is);
            JsonNode questionsNode = rootNode.get("questions");
            questionsMap = new HashMap<>();

            questionsNode.fields().forEachRemaining(entry -> {
                questionsMap.put(entry.getKey(), entry.getValue());
                System.out.println("Pregunta cargada: " + entry.getKey());
            });

        } catch (Exception e) {
            System.err.println("Error al cargar o parsear questions.json: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayQuestion(JsonNode questionNode) {
        if (questionNode == null) {
            System.err.println("Nodo de pregunta nulo.");
            return;
        }

        String questionText = questionNode.get("questionText").asText();
        System.out.println("Mostrando pregunta: " + questionText);
        questionLabel.setText(questionText);
        optionsContainer.getChildren().clear();

        JsonNode optionsNode = questionNode.get("options");
        if (optionsNode != null && optionsNode.isArray()) {
            HBox buttonHBox = new HBox(20);
            buttonHBox.setAlignment(Pos.CENTER);

            for (JsonNode option : optionsNode) {
                String optionText = option.get("text").asText();
                System.out.println("Creando botón para opción: " + optionText);
                Button button = new Button(optionText);
                button.setStyle("-fx-background-color: #98FB98; -fx-text-fill: #000000; -fx-font-size: 24px; -fx-font-weight: bold; -fx-padding: 20 40; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3); -fx-cursor: hand;");
                button.setOnAction(event -> handleAnswer(option));
                buttonHBox.getChildren().add(button);
            }
            optionsContainer.getChildren().add(buttonHBox);
        }
    }

    private void handleAnswer(JsonNode selectedOption) {
        if (selectedOption.has("nextQuestionId")) {
            currentQuestionId = selectedOption.get("nextQuestionId").asText();
            displayQuestion(questionsMap.get(currentQuestionId));
        } else if (selectedOption.has("pokemonResult")) {
            String pokemonName = selectedOption.get("pokemonResult").asText();
            System.out.println("¡Tu Pokémon ideal es: " + pokemonName + "!");
            showPokemonCard(pokemonName);
        }
    }

    private void showPokemonCard(String pokemonName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/labprofessoroak/pokemon-card-view.fxml"));
            VBox pokemonCardView = loader.load();

            PokemonCardController cardController = loader.getController();
            cardController.setPokemonName(pokemonName);

            Scene pokemonScene = new Scene(pokemonCardView, 1200, 800);
            Stage stage = (Stage) questionLabel.getScene().getWindow();
            stage.setScene(pokemonScene);
            stage.setTitle("Tu Pokémon Ideal");

        } catch (Exception e) {
            System.err.println("Error al cargar la tarjeta del Pokémon: " + e.getMessage());
            e.printStackTrace();
        }
    }
}