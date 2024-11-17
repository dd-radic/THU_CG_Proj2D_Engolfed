package com.thu_cg_proj2d_engolfed;

import com.thu_cg_proj2d_engolfed.levels.LevelManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** <h1>		Engolfed			</h1>
 * <p>			A simple 2D golfing video-game themed around fire	</p>
 * <p>			THU - Technische Hochschule Ulm a.k.a. Ulm University of Applied Sciences	</p>
 * <p>			CTS5 - Computer Science B.Sc. program semester 5	</p>
 * <p>			2D Project for "Computer Graphics" module	</p>
 *	@author 	David-Dominik Radić
 * @version 	1.0 (2024-11-07)
 */
public class AppEngolfed extends Application {
	private LevelManager levelManager = new LevelManager();
	private AnimationTimer at;
	private long lastFrameTime = 0;

	int frameCounter = 0;

	@Override
	public void start(Stage stage) throws Exception {
		at = initAnimTimer(stage);
		stage.setScene(levelManager.loadNewLevel(1));
		at.start();
		stage.setResizable(false);
		stage.setTitle("Engolfed: The Game");
		stage.show();
	}

	private void updateFrame(Stage s) {
		levelManager.updateCurrentLevel();
		frameCounter++;
	}

	private AnimationTimer initAnimTimer(Stage s) {
		double framerate = 60;
		double delta = (1000000000.0/ framerate);
		return new AnimationTimer() {
			@Override
			public void handle(long now) {
				// Calculate the time passed since the last frame
				if (lastFrameTime == 0) {
					lastFrameTime = now;
					return;
				}

				long elapsedTime = now - lastFrameTime;

				// If the elapsed time exceeds or equals the frame time, update the game logic
				if (elapsedTime >= delta) {
					updateFrame(s);
					lastFrameTime = now;
				}
			}
		};
	}

	public static void main(String[] args) {
		launch(args);
	}
}