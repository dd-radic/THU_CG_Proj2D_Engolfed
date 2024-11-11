package com.thu_cg_proj2d_engolfed.levels;

import com.thu_cg_proj2d_engolfed.gameobjects.GameObject;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class LevelManager{
	private Scene[] levels;
	public Scene currentLevel;
	private Pane root;
	public byte index;

	public LevelManager(){
		init(levels);
		this.index = 0;
		this.currentLevel = this.levels [this.index];
	}

	public Scene loadNewLevel(int i){
		if (i >= this.levels.length) return this.currentLevel;

		this.index = (byte) i;
		this.currentLevel = this.levels [this.index];
		return this.currentLevel;
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

	private void init(Scene[] levels){
		this.levels = new Scene[20];
		for (int i = 0; i < this.levels.length; i++){
			this.levels[i] = new Scene(createContent(i));
		}

	}

	private Parent createContent(int i) {
		root = new Pane();
		root.setPrefSize(600, 600);
		GameObject test = new GameObject();
		test.setTranslateX(100);
		test.setTranslateY(100);
		root.getChildren().add(test);
		switch (i){
			case 1:
				test = new GameObject();
				for (Shape s: test.layers) {
					s.setTranslateX(100);
					s.setTranslateY(100);
					root.getChildren().add(s);
				}
			default: break;
		}
		return root;
	}


}
