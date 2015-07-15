package net.eseb.jfxttt;

public class Human implements Inputer
{
	Model model;
	public static String type = "Human";

	public Human(Model model) {
		this.model = model;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Piece mkAMove(Player as_player) {
		throw new IllegalStateException("can't play.");
	}
}
