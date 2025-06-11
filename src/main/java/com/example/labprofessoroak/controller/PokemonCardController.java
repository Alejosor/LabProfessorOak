package com.example.labprofessoroak.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class PokemonCardController {

    @FXML
    private Label pokemonNameLabel;
    @FXML
    private ImageView pokemonImageView;
    @FXML
    private Label pokemonTypesLabel;
    @FXML
    private Label pokemonAbilitiesLabel;
    @FXML
    private Label pokemonWeightLabel;
    @FXML
    private Label pokemonHeightLabel;

    private String pokemonName;

    public void setPokemonName(String name) {
        this.pokemonName = name;
        loadPokemonData(name);
    }

    private void loadPokemonData(String name) {
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase() + "/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::parseAndDisplayPokemonData)
                .exceptionally(e -> {
                    System.err.println("Error al obtener datos del Pokémon: " + e.getMessage());
                    Platform.runLater(() -> pokemonNameLabel.setText("Error al cargar: " + name));
                    return null;
                });
    }

    private void parseAndDisplayPokemonData(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(responseBody);

            String name = rootNode.get("name").asText();
            JsonNode spritesNode = rootNode.get("sprites");
            String imageUrl = spritesNode.get("front_default").asText();

            String types = "N/A";
            if (rootNode.has("types")) {
                types = StreamSupport.stream(rootNode.get("types").spliterator(), false)
                        .map(typeNode -> typeNode.get("type").get("name").asText())
                        .collect(Collectors.joining(", "));
            }

            String abilities = "N/A";
            if (rootNode.has("abilities")) {
                abilities = StreamSupport.stream(rootNode.get("abilities").spliterator(), false)
                        .map(abilityNode -> abilityNode.get("ability").get("name").asText())
                        .collect(Collectors.joining(", "));
            }

            double weight = rootNode.get("weight").asDouble() / 10.0; // Convertir de decagrams a kg
            double height = rootNode.get("height").asDouble() / 10.0; // Convertir de decimetros a metros

            // Asegurar que las variables sean efectivamente final para el uso en la lambda
            final String finalName = capitalizeFirstLetter(name);
            final String finalTypes = types;
            final String finalAbilities = abilities;
            final double finalWeight = weight;
            final double finalHeight = height;
            final String finalImageUrl = imageUrl;

            Platform.runLater(() -> {
                pokemonNameLabel.setText(finalName);
                pokemonTypesLabel.setText(finalTypes);
                pokemonAbilitiesLabel.setText(finalAbilities);
                pokemonWeightLabel.setText(finalWeight + " kg");
                pokemonHeightLabel.setText(finalHeight + " m");
                
                if (finalImageUrl != null && !finalImageUrl.isEmpty()) {
                    pokemonImageView.setImage(new Image(finalImageUrl));
                }
            });

        } catch (Exception e) {
            System.err.println("Error al parsear datos del Pokémon: " + e.getMessage());
            e.printStackTrace();
            Platform.runLater(() -> pokemonNameLabel.setText("Error de parseo para: " + pokemonName));
        }
    }

    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @FXML
    private void handleRestartButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/labprofessoroak/decision-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 800);
            Stage stage = (Stage) pokemonNameLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Árbol de Decisiones Pokémon");
        } catch (Exception e) {
            System.err.println("Error al volver a la pantalla de inicio: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCloseButtonAction() {
        Platform.exit();
    }
} 