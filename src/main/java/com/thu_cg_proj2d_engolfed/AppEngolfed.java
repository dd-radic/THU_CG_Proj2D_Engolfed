package com.thu_cg_proj2d_engolfed;

import com.thu_cg_proj2d_engolfed.gameobjects.GameObject;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/** <h1>		Engolfed			</h1>
 * <p>			A simple 2D golfing video-game themed around fire	</p>
 * <p>			THU - Technische Hochschule Ulm a.k.a. Ulm University of Applied Sciences	</p>
 * <p>			CTS5 - Computer Science B.Sc. program semester 5	</p>
 * <p>			2D Project for "Computer Graphics" module	</p>
 *	@author 	David-Dominik Radić
 * @version 	1.0 (2024-11-07)
 */

public class AppEngolfed extends Application {

	private Pane root;

	private Parent createContent() {
		root = new Pane();
		root.setPrefSize(600, 600);
		GameObject test = new GameObject();
		root.getChildren().add(test.sprite);
		return root;
	}
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(createContent()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}