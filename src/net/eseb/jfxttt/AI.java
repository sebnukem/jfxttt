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
	public Piece mkAMove(Player as_player) {
		Board board = new Board(model.getBoard());

		// look for a winning move
		for (Piece p : board.getBoard()) {
			if (p.isOccupied()) continue;
			p.setOwner(as_player);
			if (board.isWon() != null) return p;
			p.setOwner(Player.NONE);
		}
		// look for non-losing move
		for (Piece p : board.getBoard()) {
			if (p.isOccupied()) continue;
			p.setOwner(as_player.getOpponent(as_player));
			if (board.isWon() != null) return p;
			p.setOwner(Player.NONE);
		}
		// random move
		int watchdog = board.getLength() + 1;
		int index = rand.nextInt(board.getLength());
		while (--watchdog > 0) {
			Piece p = board.getPiece(index);
			if (!p.isOccupied()) return p;
			index = (index + 1) % board.getLength();
		}
		throw new IllegalStateException("can not play.");
	}
}
