package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Fire extends GameObject{
	public int currentCycle;

	@Override
	protected Shape[] defineLayers() {
		return new Shape[]{
				new Polygon(10, 0, 0, 10, 20, 10)
		};
	}

	@Override
	public void update() {

	}

	@Override
	public boolean collides() {
		return false;
	}
}
