package net.eseb.jfxttt;

import java.util.Arrays;

public class Board
{
	public static final int BOARD_SIZE = 3;

	private Piece[] board = new Piece[BOARD_SIZE * BOARD_SIZE]; // board is always a square

	public Board() {
		for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) setPiece(i, new Piece(i));
	}

	public Board(Board aboard) {
		board = new Piece[BOARD_SIZE * BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) setPiece(i, new Piece(i, aboard.getPiece(i).getOwner()));
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (Piece p : board) sb.append(p.getOwner().getSymbol());
		sb.append("]");
		return sb.toString();
	}

	public Piece[] getBoard() {
		return board;
	}

	public int getLength() {
		return BOARD_SIZE * BOARD_SIZE;
	}

	public void reset() {
		for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++)
			getPiece(i).setOwner(Player.NONE, false).setWinning(false);
		System.out.println(this);
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

	public static int rc2i(int row, int col) {
		return row * BOARD_SIZE + col;
	}
}
