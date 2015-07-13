package net.eseb.jfxttt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Model
{
	public final int BOARD_SIZE = 3;

//	@SuppressWarnings("unchecked")
//	public ObjectProperty<Player>[][] board = new ObjectProperty[BOARD_SIZE][BOARD_SIZE];
	public Piece[][] board = new Piece[BOARD_SIZE][BOARD_SIZE];

	public Piece getPiece(int row, int col) {
		return board[row][col];
	}

	public void reset() {
		for (int r = 0; r < BOARD_SIZE; r++)
			for (int c = 0; c < BOARD_SIZE; c++)
//				board[r][c] = new SimpleObjectProperty<>(Player.NONE);
				board[r][c] = new Piece();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
