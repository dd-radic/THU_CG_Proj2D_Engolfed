package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Crate extends GameObject{
	boolean lit;
	Fire fire;

	public Crate(int x, int y){
		super(x,y);
		this.lit = false;
		this.fire = new Fire(-10,10, 40);
		this.fire.setVisible(false);
		getChildren().add(this.fire);
	}


	@Override
	protected Shape[] defineLayers() {
		return new Shape[]{new Rectangle(40, 10, Color.BROWN)};
	}

	@Override
	public void update() {
		if (lit) this.fire.setVisible(true);
	}

}
