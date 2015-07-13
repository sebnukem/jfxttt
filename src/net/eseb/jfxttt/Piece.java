package net.eseb.jfxttt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class Piece extends StackPane
{
	private ObjectProperty<Player> owner = new SimpleObjectProperty<>(Player.NONE);

	public Piece() {
	}

	public Player getOwner() {
		return owner.get();
	}

	public void setOwner(Player anowner) {
		owner.set(anowner);
	}

	public void draw() {
		getStyleClass().add("tile");
	
	}
}
