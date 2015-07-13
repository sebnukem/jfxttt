package net.eseb.jfxttt;

import java.util.Arrays;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public enum Player
{
	NONE(null),
	X("X"),
	O("O");
	
	private String symbol; // X | O
	//private type;

	private Player(String asymbol) {
		symbol = asymbol;
	}

	@Override public String toString() {
		return symbol == null ? "no player" : "Player " + symbol;
	}

	public String getSymbol() {
		return symbol;
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
