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
		Board board = model.getBoard(); // using actual board so it needs to be left pristine when returning

		// look for a winning move
		for (Piece p : board.getBoard()) {
			if (p.isOccupied()) continue;
			p.setOwner(as_player, false);
			if (board.isWon() != null) {
				p.setOwner(Player.NONE, false);
				return delayed(p);
			}
			p.setOwner(Player.NONE, false);
		}
		// look for non-losing move
		for (Piece p : board.getBoard()) {
			if (p.isOccupied()) continue;
			p.setOwner(as_player.getOpponent(as_player), false);
			if (board.isWon() != null) {
				p.setOwner(Player.NONE, false);
				return delayed(p);
			}
			p.setOwner(Player.NONE, false);
		}
		// random move
		int watchdog = board.getLength() + 1;
		int index = rand.nextInt(board.getLength());
		while (--watchdog > 0) {
			Piece p = board.getPiece(index);
			if (!p.isOccupied()) {
				return delayed(p);
			}
			index = (index + 1) % board.getLength();
		}
		throw new IllegalStateException("can not play.");
	}
	
	private Piece delayed(Piece o){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// noop
		}
		return o;
	}
}
