package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Hole extends GameObject{
	public boolean open;

	public Hole(int x, int y) {
		super(x,y);
		this.open = false;
	}

	public void open(boolean hasBeenOpened) {
		this.open = hasBeenOpened;
	}

	@Override
	protected Shape[] defineLayers() {
		return new Shape[]{new Circle(18, Color.BLACK), new Circle(15, Color.GREENYELLOW)};
	}

	@Override
	public void update() {
		if (open) layers[1].setVisible(false);
	}

}
