package net.eseb.jfxttt;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup
{
	public static void pop(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		if (title != null) window.setTitle(title);
		window.setMinWidth(200);

		Label label = new Label(message);

		VBox layout = new VBox();
		layout.setPadding(new Insets(20,20,20,20));
		layout.getChildren().addAll(label);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		scene.setOnKeyPressed(e -> window.close());
		scene.setOnMouseClicked(e -> window.close());

		window.setScene(scene);
		window.show();
	}

}
