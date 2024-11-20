package com.thu_cg_proj2d_engolfed.gameobjects;

import com.thu_cg_proj2d_engolfed.levels.Level;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Ball extends GameObject{
	Point2D lastDist = new Point2D(0,0);
	Point2D velocity = new Point2D(0,0);
	Point2D[] collisionPoints = new Point2D[8];
	byte collision;

	Line drag = new Line(0,0,0,0);
	double friction = 0.98;

	Field field = new Field(0,0, 0);
	Fire fire;

	public Ball(double x, double y){
		super(x, y);
		drag.setStroke(Color.LIGHTGRAY);
		drag.setStrokeWidth(2);
		drag.setVisible(false);
		this.collision = 0;
		getChildren().addAll(drag);

		setOnMouseDragged(this::handleMouseDragged);
		setOnMouseReleased(this::handleMouseReleased);

		generateCollisionPoints();
		this.fire = new Fire(-15,15, 50);
		getChildren().add(this.fire);
	}

	public Circle getCircleFromObject() {
		for (Node n: this.getChildren())
			if (n instanceof Circle)
				return (Circle) n;

		System.out.println("Error fetching Circle (Ball.java::getCircleFromObject)");
		return new Circle();
	}

	private void generateCollisionPoints() {
		Point2D origin = new Point2D(this.getTranslateX(), this.getTranslateY());
		Point2D[] offset = new Point2D[8];

		for (int i = 0; i < collisionPoints.length; i++){
			offset[i] = new Point2D(	f(i), 	f(i-2));
			offset[i] = offset[i].normalize().multiply(getCircleFromObject().getRadius());
			this.collisionPoints[i] = new Point2D(origin.getX()+offset[i].getX(), origin.getY() - offset[i].getY());
		}
	}

	private double f(int x){
		return (Math.sin((x*Math.PI)/4));
	}

	private void getFieldFromLevel() {
		if (this.getParent() != null) {
			Level a = (Level) this.getScene().getRoot();
			for (Node n : a.getChildrenUnmodifiable()) {
				if (n instanceof Field) {
					this.field = (Field) n;
				}
			}
		}
	}

	private void handleMouseReleased(MouseEvent mouseEvent) {
		this.lastDist = new Point2D(drag.getStartX(), drag.getStartY());
		this.velocity = new Point2D(-lastDist.getX(), -lastDist.getY());
		drag.setVisible(false);


	}

	private void handleMouseDragged(MouseEvent mouseEvent) {
		this.lastDist = new Point2D((mouseEvent.getSceneX()-this.getTranslateX()), mouseEvent.getSceneY()-this.getTranslateY());
		if (lastDist.magnitude() > 100){
			lastDist = lastDist.normalize().multiply(100);
		}
		this.drag.setStartX(lastDist.getX());
		this.drag.setStartY(lastDist.getY());
		drag.setVisible(true);
	}

	@Override
	protected Shape[] defineLayers() {
		Shape[] layers = new Shape[1];
		layers[0] = new Circle(15, Color.DARKGREY);
		return layers;
	}

	@Override
	public void update() {
		boolean collides = collides();
		setTranslateX(getTranslateX() + (velocity.getX())/5);
		setTranslateY(getTranslateY() + (velocity.getY())/5);
		if (velocity.magnitude() > 2)
			velocity = new Point2D(velocity.getX()*friction, velocity.getY()*friction);
		else velocity = new Point2D(0,0);
		this.fire.update();
	}

	public boolean collides() {
		this.collision &=0;
		this.getFieldFromLevel();
		generateCollisionPoints();


		boolean collidesBottomWall =(!(this.field.contains(collisionPoints[7].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[0].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[1].subtract(this.field.getTranslateX(), this.field.getTranslateY()))));

		boolean collidesTopWall = (!(this.field.contains(collisionPoints[3].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[4].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[5].subtract(this.field.getTranslateX(), this.field.getTranslateY()))));

		boolean collidesRightWall =(!(this.field.contains(collisionPoints[1].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[2].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[3].subtract(this.field.getTranslateX(), this.field.getTranslateY()))));

		boolean collidesLeftWall = (!(this.field.contains(collisionPoints[5].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[6].subtract(this.field.getTranslateX(), this.field.getTranslateY())) &&
				this.field.contains(collisionPoints[7].subtract(this.field.getTranslateX(), this.field.getTranslateY()))));

		if (collidesRightWall && collidesLeftWall)
			if (collidesTopWall || collidesBottomWall){
				collidesRightWall = false;
				collidesLeftWall = false;
			}

		if (collidesTopWall && collidesBottomWall)
			if (collidesLeftWall || collidesRightWall){
				collidesTopWall = false;
				collidesBottomWall = false;
			}


		boolean collidesWall = (collidesLeftWall || collidesRightWall || collidesBottomWall || collidesTopWall) ;


		if (collidesRightWall || collidesLeftWall)
			this.velocity = new Point2D(-velocity.getX(), velocity.getY());
		if (collidesBottomWall || collidesTopWall)
				this.velocity = new Point2D(velocity.getX(), -velocity.getY());



		Level local = (Level)this.getScene().getRoot();

		if(intersectsHitbox(local.hole) && local.hole.open){
			local.startLocalTimer(60);
			velocity = new Point2D(0,0);
			setTranslateX(local.hole.getTranslateX());
			setTranslateY(local.hole.getTranslateY());
			if (getScaleX() > 0){
				setScaleX(getScaleX()-0.04);
				setScaleY(getScaleY()-0.04);
			}
		}


		if (local.crates != null && !local.crates.isEmpty())
			for (Crate c : local.crates){
				if (intersectsHitbox(c)){
					c.lit = true;
				}
			}

		return collidesWall;
	}

	private boolean intersectsHitbox(GameObject s) {
		for (int i = 0; i < collisionPoints.length; i++)
			if (s.contains(collisionPoints[i].subtract(s.getTranslateX(), s.getTranslateY())))
				return true;
		return false;
	}
}
