package com.thu_cg_proj2d_engolfed.levels;

import com.thu_cg_proj2d_engolfed.buttons.*;
import com.thu_cg_proj2d_engolfed.gameobjects.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public abstract class Level extends Parent {
	public long localTimer = -1;
	int score = 0;
	int par = 0;
	public boolean winCon = false;
	List<Text> display = new ArrayList<Text>();

	public Ball ball;
	public Hole hole;
	public Field field;

	public List<Button> buttons = new ArrayList<Button>();
	public List<Crate> crates = new ArrayList<Crate>();
	public List<Water> puddles = new ArrayList<Water>();



	public Level() {
		score = 0;
		localTimer = -1;
		initParts();
	}

	private void initParts() {
		getChildren().add(new Rectangle(993 ,559, Color.LIGHTGREEN));
		initField();
		if (this.field != null) getChildren().add(field);
		initPuddles();
		if (this.puddles != null)
			if (!this.puddles.isEmpty())
				this.getChildren().addAll(this.puddles);
		initCrates();
		if (this.crates != null)
			if (!this.crates.isEmpty())
				this.getChildren().addAll(this.crates);
		initHole();
		if (this.hole != null) getChildren().add(hole);
		initBall();
		if (this.ball != null) getChildren().add(ball);
		initButtons();
		if (this.buttons != null)
			if (!this.buttons.isEmpty())
				this.getChildren().addAll(this.buttons);
		initDisplay();
		if (this.display != null)
			if (!this.display.isEmpty())
				this.getChildren().addAll(this.display);
	}

	public Level (Level level){
		score = level.score;
		localTimer = -1;
		initParts();
	}

	public void startLocalTimer(long frames){
		if (!(localTimer > 0))
			this.localTimer = frames;
	}

	protected abstract void initField();
	protected abstract void initPuddles();
	protected abstract void initCrates();
	protected abstract void initHole();
	protected abstract void initBall();
	protected abstract void initButtons();
	protected abstract void initDisplay();

	public abstract void updateAdditional();
	public abstract void onTimeout();

	public void update(){
		updateAdditional();

		for (Node child : getChildren()){
			if (child instanceof GameObject){
				((GameObject) child).update();
			}
		}

		if (crates!=null && !crates.isEmpty()) {
			int i = 0;
			for (Crate c : crates) {
				if (!c.lit) ++i;
			}
			winCon = i == 0;
		}
		else winCon = true;

		if (localTimer > 0) localTimer--;
		if (localTimer == 0) {
			onTimeout();
			localTimer = -1;
		}
		hole.open(winCon);
	}

	public void loadNew(Level lvl){
		getScene().setRoot(lvl);
	}

}
