package net.eseb.jfxttt;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class Piece extends StackPane
{
	private static Controller controller;
	private Player player;
	private boolean winning;
	private int index;

	public Piece(int index) {
		this(index, Player.NONE);
	}
	public Piece(int index, Player owner) {
		this.index = index;
		this.player = owner;
		winning = false;
		getStyleClass().add("tile");
		setPrefSize(9999, 9999);

		paint();

		setOnMouseClicked(e -> controller.onCellClicked(this));
	}

	public static void setController(Controller acontroller) {
		controller = acontroller;
	}

	public int getIndex() {
		return index;
	}

	public boolean isOccupied() {
		return player != Player.NONE;
	}

	public boolean sameOwner(Piece o) {
		return player == o.player;
	}

	public Player getOwner() {
		return player;
	}

	public Piece setOwner(Player owner) {
		player = owner;
		paint();
		return this;
	}

	public Piece setWinning(boolean win) {
		winning = win;
		if (winning) getStyleClass().add("winning_tile"); else getStyleClass().remove("winning_tile");
		paint();
		return this;
	}

	public Piece paint() {
		// TODO make pad, strokeWidth stage size dependent
		int pad = 16;

		getChildren().clear();
		
		if (player == Player.NONE) {
			Label ci = new Label("" + index);
			Piece.setAlignment(ci, Pos.BOTTOM_CENTER);
			this.getChildren().add(ci);
			return this;
		}
		if (player == Player.X) {
			Line line1 = new Line(pad, pad, this.getWidth() - pad, this.getHeight() - pad);
			line1.setStrokeWidth(10.0);
			line1.setStroke(new Color(0,0,1,1));
			line1.endXProperty().bind(this.widthProperty().subtract(pad));
			line1.endYProperty().bind(this.heightProperty().subtract(pad));
			Line line2 = new Line(pad, this.getHeight() - pad, this.getWidth() - pad, pad);
			line2.setStrokeWidth(10.0);
			line2.setStroke(new Color(0,0,1,1));
			line2.startYProperty().bind(this.heightProperty().subtract(pad));
			line2.endXProperty().bind(this.widthProperty().subtract(pad));
			this.getChildren().addAll(line1, line2);
			return this;
		}
		if (player == Player.O) {
			Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - pad, this.getHeight() / 2 - pad);
			ellipse.setStrokeWidth(10.0);
			ellipse.centerXProperty().bind(this.widthProperty().divide(2));
			ellipse.centerYProperty().bind(this.heightProperty().divide(2));
			ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(pad));
			ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(pad));
			ellipse.setStroke(new Color(1,0,0,1));
			ellipse.setFill(new Color(0,0,0,0));
			this.getChildren().add(ellipse);
			return this;
		}
		return this;
	}
}
