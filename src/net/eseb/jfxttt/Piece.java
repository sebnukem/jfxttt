package net.eseb.jfxttt;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Piece extends StackPane
{
//	private ObjectProperty<Player> owner = new SimpleObjectProperty<>(Player.NONE);
	private static Controller controller;
	private Player owner;
	private boolean winning = false;

	public Piece() {
		this(Player.NONE);
	}
	public Piece(Player owner) {
		this.owner = owner;
		winning = false;
		getStyleClass().add("tile");
		setPrefSize(9999, 9999);

//		Light.Distant light = new Light.Distant(-135, 45, Color.WHITE);
//		light.setAzimuth(-135);
//		light.setElevation(45);
//		Lighting l = new Lighting(light);
//		setEffect(l);

		paint();

		this.setOnMouseClicked(e -> controller.onCellClicked(this));
	}

	public static void setController(Controller acontroller) {
		controller = acontroller;
	}

	public boolean isOccupied() {
		return owner != Player.NONE;
	}
	
	public boolean sameOwner(Piece o) {
		return owner == o.owner;
	}

	public Player getOwner() {
//		return owner.get();
		return owner;
	}

	public void setOwner(Player anowner) {
//		owner.set(anowner);
		owner = anowner;
		paint();
	}

	public Piece setWinning() {
		winning = true;
		getStyleClass().add("winning_tile");
		paint();
		return this;
	}

	private Piece paint() {
		// TODO make pad, strokeWidth stage size dependent
		int pad = 16;

		this.getChildren().clear();

		if (owner == Player.X) {
			Line line1 = new Line(pad, pad, this.getWidth() - pad, this.getHeight() - pad);
			line1.setStrokeWidth(10.0);
			line1.setStroke(new Color(0,0,1,0.8));
			line1.endXProperty().bind(this.widthProperty().subtract(pad));
			line1.endYProperty().bind(this.heightProperty().subtract(pad));
			Line line2 = new Line(pad, this.getHeight() - pad, this.getWidth() - pad, pad);
			line2.setStrokeWidth(10.0);
			line2.setStroke(new Color(0,0,1,0.8));
			line2.startYProperty().bind(this.heightProperty().subtract(pad));
			line2.endXProperty().bind(this.widthProperty().subtract(pad));
			this.getChildren().addAll(line1, line2);
			return this;
		}
		if (owner == Player.O) {
			Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - pad, this.getHeight() / 2 - pad);
			ellipse.setStrokeWidth(10.0);
			ellipse.centerXProperty().bind(this.widthProperty().divide(2));
			ellipse.centerYProperty().bind(this.heightProperty().divide(2));
			ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(pad));
			ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(pad));
			ellipse.setStroke(new Color(1,0,0,0.8));
			ellipse.setFill(new Color(0,0,0,0));
			this.getChildren().add(ellipse);
			return this;
		}
		return this;
	}
}
