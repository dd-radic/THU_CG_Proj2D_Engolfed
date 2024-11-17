package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.concurrent.ThreadLocalRandom;

public class Fire extends GameObject{
	public int particleNum;
	public double rangeMin, rangeMax;
	public double velocity = 0.7;
	public double fade = 0.94;


	public Fire(int rangeMin, int rangeMax, int particleNum){
		super(0,0);
		this.rangeMin = rangeMin;
		this.rangeMax = rangeMax;
		this.particleNum = particleNum;
		generateInitParticles();
	}

	private void generateInitParticles() {
		for (int i = 0; i < particleNum; i++) {
			Circle temp = new Circle(2, Color.RED);
			temp.setOpacity(0);
			getChildren().add(temp);
		}
	}

	private Circle generateParticle(Circle c) {
		c.setOpacity(ThreadLocalRandom.current().nextDouble(0.6, 1));
		c.setTranslateX(ThreadLocalRandom.current().nextDouble(rangeMin, rangeMax));
		c.setTranslateY(ThreadLocalRandom.current().nextDouble(rangeMin, rangeMax));
		return c;
	}

	@Override
	protected Shape[] defineLayers() {
		return new Shape[]{new Circle()};
	}

	@Override
	public void update() {
		for (Node n : getChildren())
			if (n instanceof Circle){
				Circle c = (Circle) n;

				c.setOpacity(c.getOpacity() * fade);
				c.setTranslateY(c.getTranslateY() - velocity);

				if (c.getOpacity() < 0.2)
					c = generateParticle(c);
			}

	}

}
