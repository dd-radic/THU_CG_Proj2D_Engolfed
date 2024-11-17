package com.thu_cg_proj2d_engolfed.levels;

import com.thu_cg_proj2d_engolfed.gameobjects.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LevelManager{
	private Scene[] levels;
	public Scene currentLevel;
	private Pane root;
	public byte index;
	Field field = new Field(0,0,0);
	Ball ball = new Ball(0,0, field);

	public LevelManager(){
		initLevels();
		this.index = 0;
		this.currentLevel = this.levels [this.index];
	}

	public Scene loadNewLevel(int i){
		if (i >= this.levels.length) return this.currentLevel;

		this.index = (byte) i;
		this.currentLevel = this.levels [this.index];
		return this.loadCurrentLevel();
	}

	public Scene loadCurrentLevel(){
		return this.currentLevel;
	}

	public void updateCurrentLevel(){
		for (Node n: this.currentLevel.getRoot().getChildrenUnmodifiable())
			if (n instanceof GameObject){
				((GameObject) n).update();
			}
	}

	private void initLevels(){
		this.levels = new Scene[20];
		for (int i = 0; i < this.levels.length; i++)
			this.levels[i] = new Scene(createContent(i));
	}

	private Parent createContent(int i) {
		root = new Pane();
		root.setPrefSize(992, 558); //16:9 width with no params over 1000
		root.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
		switch (i){
			case 0:
				root.getChildren().add(new Button(200,200, Button.Options.HANDICAP_5));
				break;

			case 1:
				field = new Field(100, 100, i);
				this.ball = new Ball(300, 300, field);
				break;
			default: field = new Field(0,0,0);
			ball = new Ball (0,0,field);
			break;
		}
		root.getChildren().add(field);
		root.getChildren().add(ball);
		return root;
	}
}
