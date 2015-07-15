package net.eseb.jfxttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller
{
	private Stage stage;
	private Model model;

	@FXML private Parent root;

	@FXML private GridPane grid;

	@FXML private Label status_label;

	@FXML private Button player_x_button;

	@FXML public void onPlayerXButtonClicked(ActionEvent e) throws Exception {
		model.switchPlayerType(player_x_button, Player.X);
	}

	@FXML private Button player_o_button;

	@FXML public void onPlayerOButtonClicked(ActionEvent e) throws Exception {
		model.switchPlayerType(player_o_button, Player.O);
	}

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

	@FXML private Button help_button;

	@FXML public void onHelpButtonClicked(ActionEvent e) throws Exception {
		System.out.println("Help button clicked");
		getHelp();
	}

	public Controller(Stage astage) {
		stage = astage;
	}

	@SuppressWarnings("static-access")
	@FXML public void initialize() {
		System.out.println("Controller.initialize()");

		model = new Model(this);

		reset();

		for (int r = 0; r < model.getBoard().BOARD_SIZE; r++)
			for (int c = 0; c < model.getBoard().BOARD_SIZE; c++)
				grid.add(model.getBoard().getPiece(r,c), c, r);
	}

	public void reset() {
		model.reset();
		player_x_button.setText("" + Player.X + ": " + Player.X.getType());
		player_o_button.setText("" + Player.O + ": " + Player.O.getType());
		setStatus(model.getCurrentPlayer() + "'s turn");
		reset_button.setText("Reset");
		model.play();
	}

	public void play() {
		model.play();
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
		case "x": case "X": // switch player X type
			model.switchPlayerType(player_x_button, Player.X);
			break;
		case "o": case "O": // switch player O type
			model.switchPlayerType(player_o_button, Player.O);
			break;
		case "0": case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": // cell move
			model.inputPlay(model.getBoard().getPiece(Integer.parseInt(key, 10)));
			break;
		case "?": case "h": case "H":
			getHelp();
			break;
		}
		// TODO Type h for help box with list of keys
	}

	public void onCellClicked(Piece piece) {
		model.inputPlay(piece);
	}

	public void setStatus(String s) {
		status_label.setText(s);
	}

	private void getHelp() {
		Popup.pop("Keys", "0..8:  Play cell\n"
			+ "R:  Reset/Restart\n"
			+ "C, Esc:  Close\n"
			+ "X:  Switch player " + Player.X.getSymbol() + " type\n"
			+ "O:  Switch player " + Player.O.getSymbol() + " type\n"
		);
	}

	public static void main(String[] args) {
	}
}
