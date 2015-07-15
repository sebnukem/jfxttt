package net.eseb.jfxttt;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup
{
	public static void pop(String title, String message) {
		Stage window = new Stage();
		StackPane layout = new StackPane();
		Scene scene = new Scene(layout);

		Label label = new Label(message);
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().add(label);

		scene.setOnKeyPressed(e -> window.close());
		scene.setOnMouseClicked(e -> window.close());

		window.setScene(scene);
		window.sizeToScene();
		window.setResizable(false);
		if (title != null) window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.show();
	}
}
