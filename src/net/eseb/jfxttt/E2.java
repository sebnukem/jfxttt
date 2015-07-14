package net.eseb.jfxttt;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class E2 extends Application {
    public void start(Stage stage) {

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Label heading = new Label("Press Key");
        Label keyPressed = new Label();
        root.getChildren().addAll(heading, keyPressed);
        Scene scene = new Scene(root, 400, 300);

        scene.setOnKeyPressed((KeyEvent ke) -> keyPressed.setText("Key Pressed: " + ke.getCode()));

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}