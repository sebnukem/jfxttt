package net.eseb.jfxttt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.LightingBuilder;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class Piece extends StackPane
{
//	private ObjectProperty<Player> owner = new SimpleObjectProperty<>(Player.NONE);
	private Player owner;

	public Piece() {
		this(Player.NONE);
	}
	public Piece(Player anowner) {
		owner = anowner;
		getStyleClass().add("tile");
		setPrefSize(9999, 9999);
		Light.Distant light = new Light.Distant();
		light.setAzimuth(-135);
		light.setElevation(45);
		Lighting l = new Lighting(light);
		
		setEffect(l);
		
	}

	public Player getOwner() {
//		return owner.get();
		return owner;
	}

	public void setOwner(Player anowner) {
//		owner.set(anowner);
		owner = anowner;
	}

//	public void draw() {
//		getStyleClass().add("tile");
//	
//	}
}
