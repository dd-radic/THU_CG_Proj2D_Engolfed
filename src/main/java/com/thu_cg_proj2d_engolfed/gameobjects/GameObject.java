package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameObject extends Group {
	public Shape[] layers;
	public Point2D velocity;

	public GameObject(){
		initSprite();
		this.getChildren().addAll(layers);
		this.velocity = new Point2D(0, 0);
	}

	private void initSprite() {
		this.layers = defineLayers();
		for (Shape s : this.layers) {
			s.setStroke(Color.BLACK);
			s.setStrokeWidth(3);
		}
	}

	private Shape[] defineLayers() {
		Shape defSpriteLayer1 = Shape.union((new Rectangle(100,100)),(new Rectangle(200,0,100,150)));
		defSpriteLayer1.setFill(Color.BLUE);

		Shape defSpriteLayer2 = new Circle(50, Color.ORANGE);

		return new Shape[]{defSpriteLayer1, defSpriteLayer2};
	}

	public void update(){
		this.setTranslateX(getTranslateX() + velocity.getX());
		this.setTranslateY(getTranslateY() + velocity.getY());
	}

	private boolean collides(GameObject obj){
		return false;
	}

}
