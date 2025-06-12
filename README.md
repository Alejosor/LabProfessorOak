#  Laboratorio del Profesor Oak

¡Bienvenido al Laboratorio del Profesor Oak! Esta aplicación te ayudará a encontrar tu Pokémon ideal basado en tus preferencias a través de un árbol de decisiones interactivo.

##  Características

-  Árbol de decisiones interactivo
-  Interfaz gráfica moderna con estilos pastel
-  Preguntas personalizadas para encontrar tu Pokémon ideal
-  Tarjeta detallada del Pokémon con:
  - Imagen oficial
  - Tipos
  - Habilidades
  - Peso y altura
-  Opción para volver a empezar
-  Integración con PokeAPI

##  Requisitos

- Java 21 o superior
- JavaFX 21
- Maven

##  Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/Alejosor/LabProfessorOak.git
cd LabProfessorOak
```

2. Compila el proyecto:
```bash
mvn clean package
```

3. Ejecuta la aplicación.

## 🏗️ Estructura del Proyecto

```
LabProfessorOak/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── labprofessoroak/
│   │   │               ├── controller/
│   │   │               │   ├── DecisionController.java
│   │   │               │   └── PokemonCardController.java
│   │   │               └── ProfessorOakLab.java
│   │   └── resources/
│   │       └── com/
│   │           └── example/
│   │               └── labprofessoroak/
│   │                   ├── decision-view.fxml
│   │                   ├── pokemon-card-view.fxml
│   │                   ├── questions.json
│   │                   └── Profesor.png
├── pom.xml
└── README.md
```

##  Uso

1. Inicia la aplicación
2. Responde las preguntas sobre tus preferencias
3. Descubre tu Pokémon ideal
4. Explora los detalles de tu Pokémon
5. ¡Vuelve a empezar si quieres probar otras opciones!


## 📝 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Autores

- Pedro - [@PetusoTwo](https://github.com/PetusoTwo)
- Alejandro - [@Alejosor](https://github.com/Alejosor)

