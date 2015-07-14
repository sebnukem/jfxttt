package net.eseb.jfxttt;

import java.util.Arrays;

import javafx.scene.control.Button;

public class Model
{
	public final static int BOARD_SIZE = 3; // board is always a square

	public Controller controller;

	public Piece[] board = new Piece[BOARD_SIZE * BOARD_SIZE]; // FIXME create Board class

	public Player[] players = new Player[] {
		Player.X,
		Player.O
	};

	private AI ai = new AI(this);

	private boolean game_over = false;
	private boolean wait_on_input = false; // ready for user input
	private int current_player_index = 0;

	public Model(Controller controller) {
		this.controller = controller;
		Piece.setController(controller);
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (Piece p : board) sb.append(p.getOwner().getSymbol());
		sb.append("]");
		return sb.toString();
	}

	public static int rc2i(int row, int col) {
		return row * BOARD_SIZE + col;
	}

	public Piece getPiece(int row, int col) {
		return getPiece(rc2i(row, col));
	}
	public Piece getPiece(int index) {
		return board[index];
	}

	public Piece setPiece(int index, Piece piece) {
		return board[index] = piece;
	}

	public void reset() {
		for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
			if (getPiece(i) == null) setPiece(i, new Piece(i));
			getPiece(i).setOwner(Player.NONE).setWinning(false);
		}
		controller.reset_button.setDisable(true);
		game_over = false;
		System.out.println(this);
	}
	
	public void play() { // main loop
		while (!game_over && !wait_on_input) {
			Player current_player = getCurrentPlayer();

			if (current_player.isHuman()) {
				wait_on_input = true;
				return;
			}
			wait_on_input = false;

			if (current_player.isAI()) {
				int move_index;
				try {
					move_index = current_player.getInputer().mkAMove(current_player);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					controller.setStatus("An error occured.");
					game_over = true;
					return;
				}
				getPiece(move_index).setOwner(current_player);
				System.out.println(current_player + " plays " + move_index);
			}

			if (checkGameOver()) return;
			getNextPlayer();
		}
	}

	public void inputPlay(Piece piece) { // human input
		if (!wait_on_input) return;
		if (game_over) return;
		if (piece.isOccupied()) return;

		piece.setOwner(getCurrentPlayer());
		System.out.println("" + getCurrentPlayer().toString() + " plays " + piece.getIndex());

		if (checkGameOver()) return;
		getNextPlayer();
		
		wait_on_input = false;
		play();
	}
	
	public boolean checkGameOver() {
		System.out.println(this);
		controller.reset_button.setDisable(false);

		Piece[] winning_pieces = isWon(board);
		if (winning_pieces != null) {
			Player winner = winning_pieces[0].getOwner();
			System.out.println(winner + " won!");
			controller.setStatus(winner + " won!");
			for (Piece p : winning_pieces) p.setWinning(true);
			controller.reset_button.setText("Restart");
			game_over = true;
			return true;
		}

		if (isComplete()) {
			System.out.println("It's a draw!");
			controller.setStatus("It's a draw!");
			controller.reset_button.setText("Restart");
			game_over = true;
			return true;
		}
		game_over = false;
		return false;
	}

	public Player getCurrentPlayer() {
		return players[current_player_index];
	}

	public Player getNextPlayer() {
		current_player_index = (current_player_index + 1) % players.length;
		controller.setStatus(getCurrentPlayer() + "'s turn");
		return getCurrentPlayer();
	}
	
	public void switchPlayerType(Button button, Player player) {
		if (player == null || player == Player.NONE) return;
		player.setInputer(player.isAI() ? null : ai);
		button.setText("" + player + ": " + player.getType());
		if (wait_on_input) {
			wait_on_input = false;
			play();
		}
	}
	
	public static Piece[] isWon(Piece[] board) {
		Piece[] list;
		// rows -
		for (int row = 0; row < BOARD_SIZE; row++) {
			list = checkLine(board, rc2i(row, 0), 1);
			if (list != null) return list;
		}
		// cols |
		for (int col = 0; col < BOARD_SIZE; col++) {
			list = checkLine(board, rc2i(0, col), BOARD_SIZE);
			if (list != null) return list;
		}
		// diag \
		list = checkLine(board, rc2i(0, 0), BOARD_SIZE + 1);
		if (list != null) return list;
		// diag /
		return checkLine(board, rc2i(0, BOARD_SIZE - 1), BOARD_SIZE - 1);
	}

	private static Piece[] checkLine(Piece[] board, int at, int inc) {
		Piece[] list = new Piece[BOARD_SIZE];
		for (int i = at, c = 0; c < BOARD_SIZE; i += inc, c++) {
			Piece p = board[i];
			list[c] = p;
			if (!list[0].isOccupied() || !p.isOccupied() || !list[0].sameOwner(p)) return null;
		}
		return list;
	}
	
	public boolean isComplete() {
		return moveCount() == BOARD_SIZE * BOARD_SIZE;
	}

	public int moveCount() {
		return (int)Arrays.stream(board).filter(p -> p.isOccupied()).count();
	}
	
	public static void main(String[] args) {
		Model m = new Model(null);
		m.reset();
		m.getPiece(0).setOwner(Player.X);
		m.getPiece(3).setOwner(Player.X);
		m.getPiece(6).setOwner(Player.O);
		System.out.println(" moveCount " + m.moveCount());
		System.out.println("dmoveCount " + m.moveCount());
	}
}
