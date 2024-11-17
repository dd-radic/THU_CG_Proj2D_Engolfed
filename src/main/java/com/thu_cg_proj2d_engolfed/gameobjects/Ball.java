package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Ball extends GameObject{
	Point2D lastDist = new Point2D(0,0);
	Point2D velocity = new Point2D(0,0);
	Line vec = new Line(0,0,0,0);
	double friction = 0.975;
	Field field = new Field(0,0, 0);

	public Ball(double x, double y, Field field){
		super(x, y);
		this.field = field;
		vec.setStroke(Color.LIGHTGRAY);
		vec.setStrokeWidth(2);
		vec.setVisible(false);
		getChildren().addAll(vec);
		setOnMouseDragged(this::handleMouseDragged);
		setOnMouseReleased(this::handleMouseReleased);
	}

	private void handleMouseReleased(MouseEvent mouseEvent) {
		this.lastDist = new Point2D(vec.getStartX(), vec.getStartY());
		this.velocity = new Point2D(-lastDist.getX(), -lastDist.getY());
		vec.setVisible(false);
	}

	private void handleMouseDragged(MouseEvent mouseEvent) {
		vec.setVisible(true);
		this.lastDist = new Point2D((mouseEvent.getSceneX()-this.getTranslateX()), mouseEvent.getSceneY()-this.getTranslateY());
		if (!(lastDist.magnitude() > 100)){
			this.vec.setStartX(lastDist.getX());
			this.vec.setStartY(lastDist.getY());

		}
	}

	@Override
	protected Shape[] defineLayers() {
		Shape[] layers = new Shape[1];
		layers[0] = new Circle(15, Color.DARKGREY);
		return layers;
	}

	@Override
	public void update() {
		boolean col = collides();

		setTranslateX(getTranslateX() + (velocity.getX())/5);
		setTranslateY(getTranslateY() + (velocity.getY())/5);

		if (velocity.magnitude() > 1)
			velocity = new Point2D(velocity.getX()*friction, velocity.getY()*friction);
		else velocity = new Point2D(0,0);
	}

	@Override
	public boolean collides() {
		Point2D cC = new Point2D(this.getTranslateX(), this.getTranslateY());
		double r = ((Circle) this.getChildren().getFirst()).getRadius();
		Point2D cR = new Point2D(field.getTranslateX(), field.getTranslateY());
		Shape poly = new Rectangle(0,0);
		int i = 0;
		for (Node n : field.getChildren()) {
			if (n instanceof Rectangle) {
				poly = Shape.union(poly, (Rectangle) n);

			}
		}

		// Check if the ball's edges are inside the bounds of the combined field shape
		// Top-left check
		if (!poly.contains(cC.getX() - r, cC.getY() - r)) {
			velocity = new Point2D(-velocity.getX(), -velocity.getY());
			return false;
		}
		// Top-right check
		if (!poly.contains(cC.getX() + r, cC.getY() - r)) {
			velocity = new Point2D(-velocity.getX(), velocity.getY());
			return false;
		}
		// Bottom-left check
		if (!poly.contains(cC.getX() - r, cC.getY() + r)) {
			velocity = new Point2D(velocity.getX(), -velocity.getY());
			return false;
		}
		// Bottom-right check
		if (!poly.contains(cC.getX() + r, cC.getY() + r)) {
			velocity = new Point2D(velocity.getX(), -velocity.getY());
			return false;
		}

		// If all four checks pass, the ball is inside the field shape
		return true;
	}

}
