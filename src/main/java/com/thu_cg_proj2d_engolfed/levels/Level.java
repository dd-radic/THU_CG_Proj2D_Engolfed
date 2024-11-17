package com.thu_cg_proj2d_engolfed.levels;

import com.thu_cg_proj2d_engolfed.gameobjects.*;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.List;

public abstract class Level extends Parent {
	int index;
	Ball ball;
	Hole hole;
	Field field;

	List<Button> buttons;
	List<Crate> crates;
	List<Water> puddles;

	public Level(){

	}

}
