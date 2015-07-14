package net.eseb.jfxttt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller
{
	@SuppressWarnings("unused")
	public Main application;
	public Stage stage;
	public Scene scene;
	public Model model;

	@FXML public Parent root;

	@FXML private GridPane grid;

	@FXML private Label status_label;

	@FXML private Button reset_button;

	@FXML public void onResetButtonClicked(ActionEvent e) throws Exception {
		System.out.println("Reset button clicked");
		reset();
	}

	@FXML private Button close_button;

	@FXML public void onCloseButtonClicked(ActionEvent e) throws Exception {
		System.out.println("Close button clicked");
		stage.close();
	}
	 
//	@FXML public void xxx(KeyEvent e) throws Exception {
//		System.out.println("Key pressed");
//	}

	public Controller(Main app, Stage astage) {
		application = app;
		stage = astage;
	}

	@FXML public void initialize() {
		System.out.println("Controller.initialize()");

//		scene = new Scene(root, 500, 525);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
////		scene.setOnKeyPressed((KeyEvent e) -> System.out.println("key pressed"));
////		 scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
////	            public void handle(KeyEvent ke) {
////	            	System.out.println("key pressed");
////	                keyPressed.setText("Key Pressed: " + ke.getCode());
////	            }
////	        });
//
//		stage.setScene(scene);
//		stage.setMinWidth(120);
//		stage.setMinHeight(145);
//		stage.setTitle("JFX TTT");
////		stage.initStyle(StageStyle.UNDECORATED);
//		stage.show();

		model = new Model(this);
		reset();
		for (int r = 0; r < model.BOARD_SIZE; r++)
			for (int c = 0; c < model.BOARD_SIZE; c++)
				grid.add(model.getPiece(r,c), c, r);
		
	}
	
	public void reset() {
		model.reset();
		setStatus(model.getCurrentPlayer() + "'s turn");
	}

	public void onCellClicked(Piece piece) {
		model.play1(piece);
	}

	public void setStatus(String s) {
		status_label.setText(s);
	}

	public static void main(String[] args) {
	}
}
