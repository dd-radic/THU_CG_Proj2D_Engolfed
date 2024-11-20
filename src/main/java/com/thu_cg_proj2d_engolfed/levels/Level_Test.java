package com.thu_cg_proj2d_engolfed.levels;

import com.thu_cg_proj2d_engolfed.gameobjects.Ball;
import com.thu_cg_proj2d_engolfed.gameobjects.Crate;
import com.thu_cg_proj2d_engolfed.gameobjects.Field;
import com.thu_cg_proj2d_engolfed.gameobjects.Hole;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public class Level_Test extends Level {

	@Override
	protected void initField() {
		this.field = new Field(30,30, new Rectangle[]{
			new Rectangle(800,100),
			new Rectangle(300,400),
			new Rectangle(400,0, 100,400),
			new Rectangle(0,300, 800, 100),
		});
	}

	@Override
	protected void initPuddles() {

	}

	@Override
	protected void initCrates() {
		crates.add(new Crate(350, 90));
		crates.add(new Crate(350, 360));
		crates.add(new Crate(750, 90));
		crates.add(new Crate(750, 360));
	}

	@Override
	protected void initHole() {
		this.hole = new Hole(480, 240);
	}

	@Override
	protected void initBall() {
		this.ball = new Ball(50,250);
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
		System.exit(0);

	}
	private void next(){
		Level_Test aaa = new Level_Test();
		loadNew(aaa);
	}

}
