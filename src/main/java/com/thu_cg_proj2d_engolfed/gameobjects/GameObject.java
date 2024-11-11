package com.thu_cg_proj2d_engolfed.gameobjects;

import com.thu_cg_proj2d_engolfed.states.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class GameObject{
	public Shape[] spriteLayers;
	public GameState[] states;

	public GameObject(){
		initSprite();
		initStateMachine();
	}

	private void initSprite() {
		this.spriteLayers = defineSpriteLayers();
		for (Shape s : this.spriteLayers) {
			s.setStroke(Color.BLACK);
			s.setStrokeWidth(3);
		}
	}

	private Shape[] defineSpriteLayers() {
		Shape defSpriteLayer1 = Shape.union((new Rectangle(100,100)),(new Rectangle(200,0,100,150)));
		defSpriteLayer1.setFill(Color.BLUE);

		Shape defSpriteLayer2 = new Circle(50, Color.ORANGE);

		return new Shape[]{defSpriteLayer1, defSpriteLayer2};
	}

	private void initStateMachine() {
	}

	boolean collides(GameObject obj){
		return false;
	}
}
