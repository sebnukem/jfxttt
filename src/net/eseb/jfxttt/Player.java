package net.eseb.jfxttt;

import java.util.Arrays;

public enum Player
{
	NONE(null),
	X("✗"),
	O("○");
	
	private String symbol; // X | O
	private Inputer inputer = null; // Human by default

	private Player(String asymbol) {
		symbol = asymbol;
	}

	@Override public String toString() {
		return symbol == null ? "no player" : "Player " + symbol;
	}
	
	public Player getOpponent(Player ofplayer) {
		if (ofplayer == null || ofplayer == Player.NONE) return null;
		return ofplayer == Player.X ? Player.O : Player.X; 
	}

	public String getSymbol() {
		return symbol == null ? " " : symbol;
	}
	
	public Inputer getInputer() {
		return inputer;
	}
	
	public void setInputer(Inputer inputer) {
		this.inputer = inputer;
	}
	
	public String getType() {
		return inputer == null ? "Human" : inputer.getType();
	}

	public boolean isAI() {
		return inputer instanceof AI;
	}
	public boolean isHuman() {
		return !isAI();
	}

	public Piece mkAMove() {
		if (inputer == null) return null;
		return inputer.mkAMove(this);
	}

	public static Player find(String asymbol) {
		if (asymbol == null) return NONE;
		for (Player p : values())
			if (p.symbol != null && p.symbol.equalsIgnoreCase(asymbol)) return p;
		return NONE;
	}
	
	public static Player ffind(String asymbol) {
		return asymbol == null ? NONE : Arrays.stream(values()).filter(p -> asymbol.equalsIgnoreCase(p.symbol)).findFirst().orElse(NONE);
	}

	public static void main(String[] args) {
		System.out.println("found d X " + find("X"));
		System.out.println("found d o " + find("o"));
		System.out.println("found d a " + find("a"));
		System.out.println("found d null " + find(null));
		System.out.println("found f X " + ffind("X"));
		System.out.println("found f o " + ffind("o"));
		System.out.println("found f a " + ffind("a"));
		System.out.println("found f null " + ffind(null));
	}
}
