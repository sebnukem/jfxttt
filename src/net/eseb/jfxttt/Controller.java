package net.eseb.jfxttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller
{
	@SuppressWarnings("unused")
	private Main application;
	private Stage stage;
	@SuppressWarnings("unused")
	private Scene scene;
	private Model model;

	@FXML private Parent root;

	@FXML private GridPane grid;

	@FXML private Label status_label;

	// FIXME bind to an observable and remove the controller.reset_button.* calls from model
	@FXML public Button reset_button;

	@FXML public void onResetButtonClicked(ActionEvent e) throws Exception {
		System.out.println("Reset button clicked");
		reset();
	}

	@FXML private Button close_button;

	@FXML public void onCloseButtonClicked(ActionEvent e) throws Exception {
		System.out.println("Close button clicked");
		stage.close();
	}
	 
	public Controller(Main app, Stage astage) {
		application = app;
		stage = astage;
	}

	@FXML public void initialize() {
		System.out.println("Controller.initialize()");

		model = new Model(this);
		reset();
		for (int r = 0; r < model.BOARD_SIZE; r++)
			for (int c = 0; c < model.BOARD_SIZE; c++)
				grid.add(model.getPiece(r,c), c, r);
	}

	public void reset() {
		model.reset();
		setStatus(model.getCurrentPlayer() + "'s turn");
		reset_button.setText("Reset");
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void onSceneKeyPressed(String key) {
		System.out.println("Key pressed: " + key);
		switch(key) {
		case "r": case "R": // reset
			reset();
			break;
		case "c": case "C": case "ESCAPE": // close
			stage.close();
			break;
		case "0": case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8":
			model.play1(model.getPiece(Integer.parseInt(key, 10)));
			break;
		}
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
