package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.shape.*;

public abstract class GameObject extends Group {
	public Shape[] layers;
	public Point2D velocity;
	Parent level;

	public GameObject(){
		initSprite();
		this.getChildren().addAll(layers);
		this.velocity = new Point2D(0, 0);
		this.level = this.getScene().getRoot();
	}

	public GameObject(double x, double y){
		initSprite();
		this.getChildren().addAll(layers);
		this.velocity = new Point2D(0, 0);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}

	protected void initSprite() {
		this.layers = defineLayers();
	}

	protected abstract Shape[] defineLayers();

	public abstract void update();
}
