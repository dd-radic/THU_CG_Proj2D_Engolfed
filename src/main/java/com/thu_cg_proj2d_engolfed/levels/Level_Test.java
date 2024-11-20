package com.thu_cg_proj2d_engolfed.levels;

import com.thu_cg_proj2d_engolfed.gameobjects.Ball;
import com.thu_cg_proj2d_engolfed.gameobjects.Field;
import com.thu_cg_proj2d_engolfed.gameobjects.Hole;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public class Level_Test extends Level {
	public Level_Test(Scene scene) {
		super(scene);
	}

	@Override
	protected void initField() {
		this.field = new Field(100,100, new Rectangle[]{
			new Rectangle(700,200),
			new Rectangle(400,400),
		});
	}

	@Override
	protected void initPuddles() {

	}

	@Override
	protected void initCrates() {

	}

	@Override
	protected void initHole() {
		this.hole = new Hole(500, 200);
	}

	@Override
	protected void initBall() {
		this.ball = new Ball(300,300);
	}

	@Override
	protected void initButtons() {

	}

	@Override
	protected void initDisplay() {

	}

	@Override
	public void updateAdditional() {

	}

	@Override
	public void onTimeout() {

	}
}
