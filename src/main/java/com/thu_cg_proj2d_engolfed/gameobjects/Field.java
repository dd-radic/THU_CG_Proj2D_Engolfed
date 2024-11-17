package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Field extends GameObject{
	int type;

	public Field(int x, int y, int i){
		super(x,y);
		this.type = i;
		this.layers = redefineLayers();
	}

	private Shape[] redefineLayers() {
		Shape[] layers;
		switch (this.type){

			case 1:
				layers = new Rectangle[]{
						new Rectangle(400,400),
						new Rectangle(700,200),
				};
				break;

			default: layers = new Rectangle[]{
					new Rectangle(100,100),
			}; break;
		}
		for (Shape layer : layers) {
			layer.setFill(Color.GREENYELLOW);
		}
		this.getChildren().addAll(layers);
		return layers;
	}

	@Override
	protected Shape[] defineLayers() {
		return new Shape[0];
	}

	@Override
	public void update() {

	}

}
