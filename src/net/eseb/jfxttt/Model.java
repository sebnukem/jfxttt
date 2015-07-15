package net.eseb.jfxttt;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class Model
{
	public Controller controller;

	public Board board;

	public Player[] players = new Player[] {
		Player.X,
		Player.O
	};

	private AI ai = new AI(this);

	private boolean game_over = false;
	private int current_player_index = 0;

	public Model(Controller controller) {
		this.controller = controller;
		Piece.setController(controller);
		board = new Board();
	}

	@Override public String toString() {
		return board.toString();
	}

	public Board getBoard() {
		return board;
	}

	public void reset() {
		board.reset();
		disableReset(true);
		current_player_index = 0;
		game_over = false;
		System.out.println(this);
	}

	public void play() { // AI loop
		System.out.println("play");

		if (game_over) return;
		if (getCurrentPlayer().isHuman()) return;

		Runnable task = () -> {
			while (!game_over && getCurrentPlayer().isAI()) {

				Player current_player = getCurrentPlayer();
				Piece move;
				try {
					move = current_player.mkAMove();
					System.out.println(current_player + " plays " + move.getIndex());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					setStatus("An error occured.");
					game_over = true;
					return;
				}

				move.setOwner(current_player, false);
				disableReset(false);
				checkGameOver();

				// UI updates
				//	Platform.runLater(new Runnable() {
				//		@Override public void run() {
				//			...
				//		}
				//	});
				Platform.runLater(() -> {
					move.paint();
					controller.updateGameStatus();
				});
				
				if (!game_over) getNextPlayer();
			}
		};
		new Thread(task).start();
	}

	public void inputPlay(Piece piece) { // human input
		System.out.println("input play");

		if (game_over) return;
		if (getCurrentPlayer().isAI()) return; // AI's turn
		if (piece.isOccupied()) return;

		piece.setOwner(getCurrentPlayer(), true);
		System.out.println("" + getCurrentPlayer() + " plays " + piece.getIndex());

		disableReset(false);

		if (checkGameOver()) {
			controller.updateGameStatus();
			return;
		}

		if (getNextPlayer().isAI()) play();
	}

	public boolean checkGameOver() {
		System.out.println(this);
		if (game_over) return true;
		game_over = board.isWon() != null || board.isComplete();
		return game_over;
	}

	public void endGame() {
		game_over = true;
	}

	public Player getCurrentPlayer() {
		return players[current_player_index];
	}

	public Player getNextPlayer() {
		current_player_index = (current_player_index + 1) % players.length;
		setStatus(getCurrentPlayer() + "'s turn");
		return getCurrentPlayer();
	}

	public void switchPlayerType(Button button, Player player) {
		if (player == null || player == Player.NONE) return;
		player.setInputer(player.isAI() ? null : ai);
		button.setText("" + player + ": " + player.getType());
		if (player.isAI()) play();
	}

	public void setStatus(String s) {
		Platform.runLater(() -> controller.setStatus(s));
	}

	public void disableReset(boolean disable) {
		Platform.runLater(() -> controller.reset_button.setDisable(disable));
	}

	public static void main(String[] args) {
	}
}
