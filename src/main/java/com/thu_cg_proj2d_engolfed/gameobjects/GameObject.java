package com.thu_cg_proj2d_engolfed.gameobjects;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameObject{
	public List <Shape> components;
	public Shape sprite;

	public GameObject(){
		initSprite();
	}

	private void paintSprite(Color color) {
		this.sprite.setFill(color);
		this.sprite.setStroke(Color.BLACK);
		this.sprite.setStrokeWidth(3.0);
	}

	private void initSprite() {
		this.sprite = new Circle(0,0,0);
		initComponents();
		if (this.components != null)
			for (Shape component : this.components)
				this.sprite = Shape.union(this.sprite, component);
		paintSprite(Color.BLUE);
	}

	private void initComponents() {
		this.components = new ArrayList();
		this.components.add(new Rectangle(0,0,100,100));
		this.components.add(new Circle(200,200,50));
	}

	boolean collides(GameObject obj){
		return false;
	}
}
