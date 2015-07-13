package net.eseb.jfxttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller
{
	private Main application;
	private Stage stage;
	private Scene scene;
	
	@FXML
	private Parent root;

	@FXML
	private Button close_button;
	
	@FXML
	public void onCloseButtonClicked(ActionEvent e) throws Exception {
		System.out.println("Close button clicked");
		stage.close();
	}

	public Controller(Main app, Stage astage) {
		application = app;
		stage = astage;
	}

	@FXML
	public void initialize() {
		System.out.println("Controller.initialize()");

		scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("JFX TTT");
		stage.show();
	}

	public static void main(String[] args) {
	}
}
