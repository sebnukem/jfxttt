package net.eseb.jfxttt;

import java.util.Random;

public class AI implements Inputer
{
	public static String type = "AI";
	Model model;
	private Random rand = new Random();
	
	public AI(Model model) {
		this.model = model;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public int mkAMove(Player as_player) {
		Piece[] board = cloneBoard();

		// look for a winning move
		for (Piece p : board) {
			if (p.isOccupied()) continue;
			p.setOwner(as_player);
			if (Model.isWon(board) != null) return p.getIndex(); // FIXME return piece
			p.setOwner(Player.NONE);
		}
		// look for non-loosing move
		for (Piece p : board) {
			if (p.isOccupied()) continue;
			p.setOwner(as_player.getOpponent(as_player));
			if (Model.isWon(board) != null) return p.getIndex(); // FIXME return piece
			p.setOwner(Player.NONE);
		}
		// random move
		int watchdog = board.length + 1;
		int index = rand.nextInt(board.length);
		while (--watchdog > 0) {
			Piece p = board[index];
			if (!p.isOccupied()) return index;
			index = (index + 1) % board.length;
		}
		throw new IllegalStateException("can not play.");
	}
	
	private Piece[] cloneBoard() {
		return model.board.clone();
	}
}
