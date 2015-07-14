package net.eseb.jfxttt;

import java.util.Arrays;

public class Model
{
	public final int BOARD_SIZE = 3; // board is always a square

	public Controller controller;

	public Piece[] board = new Piece[BOARD_SIZE * BOARD_SIZE];

	public Player[] players = new Player[] {
		Player.X,
		Player.O
	};

	private boolean done = false;
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

	public int rc2i(int row, int col) {
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
			if (getPiece(i) == null) setPiece(i, new Piece());
			getPiece(i).setOwner(Player.NONE).setWinning(false);
		}
		done = false;
		System.out.println(this);
	}

	public void play1(Piece piece) {
		if (done) return;
		if (piece.isOccupied()) return;
		
		piece.setOwner(getCurrentPlayer());
		System.out.println(this);

		Piece[] winning_pieces = isWon();
		if (winning_pieces != null) {
			Player winner = winning_pieces[0].getOwner();
			System.out.println(winner + " won!");
			controller.setStatus(winner + " won!");
			for (Piece p : winning_pieces) p.setWinning(true);
			done = true;
			return;
		}
		
		if (isComplete()) {
			System.out.println("It's a draw!");
			controller.setStatus("It's a draw!");
			done = true;
			return;
		}
		
		getNextPlayer();
	}

	public Player getCurrentPlayer() {
		return players[current_player_index];
	}

	public Player getNextPlayer() {
		current_player_index = (current_player_index + 1) % players.length;
		controller.setStatus(getCurrentPlayer() + "'s turn");
		return getCurrentPlayer();
	}
	
	public Piece[] isWon() {
		Piece[] list;
		// rows -
		for (int row = 0; row < BOARD_SIZE; row++) {
			list = checkLine(rc2i(row, 0), 1);
			if (list != null) return list;
		}
		// cols |
		for (int col = 0; col < BOARD_SIZE; col++) {
			list = checkLine(rc2i(0, col), BOARD_SIZE);
			if (list != null) return list;
		}
		// diag \
		list = checkLine(rc2i(0, 0), BOARD_SIZE + 1);
		if (list != null) return list;
		// diag /
		return checkLine(rc2i(0, BOARD_SIZE - 1), BOARD_SIZE - 1);
	}

	private Piece[] checkLine(int at, int inc) {
		Piece[] list = new Piece[BOARD_SIZE];
		for (int i = at, c = 0; c < BOARD_SIZE; i += inc, c++) {
			Piece p = getPiece(i);
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
