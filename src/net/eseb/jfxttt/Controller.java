package net.eseb.jfxttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller
{
	@SuppressWarnings("unused")
	private Main application;
	private Stage stage;
	private Scene scene;
	private Model model;

	@FXML private Parent root;

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

	public Controller(Main app, Stage astage) {
		application = app;
		stage = astage;
	}

	@FXML public void initialize() {
		System.out.println("Controller.initialize()");

		scene = new Scene(root, 500, 525);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		stage.setScene(scene);
		stage.setMinWidth(120);
		stage.setMinHeight(145);
		stage.setTitle("JFX TTT");
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();

		model = new Model(this);
		reset();
	}
	
	public void reset() {
		model.reset();
		grid.getChildren().clear();
		for (int r = 0; r < model.BOARD_SIZE; r++)
			for (int c = 0; c < model.BOARD_SIZE; c++)
				grid.add(model.getPiece(r,c), c, r);
		setStatus("");
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
