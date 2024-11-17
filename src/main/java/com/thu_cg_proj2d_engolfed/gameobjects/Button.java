package com.thu_cg_proj2d_engolfed.gameobjects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Button extends GameObject{
	public enum Options{MAIN, START, NEXT, EXIT, HANDICAP_0, HANDICAP_5, HANDICAP_10}
	Options option;

	public Button(double x, double y, Options o){
		super(x,y);
		this.option = o;
		setText(this.option);
		setEffect(this.option);
	}

	private void setEffect(Options o) {
	}

	private void setText(Options o) {
		Text txt = new Text();
		Font font = Font.font("Courier New", FontWeight.BLACK,40);
		txt.setFont(font);
		txt.setFill(Color.WHITE);
		txt.setTranslateX(20);
		txt.setTranslateY(40);
		switch (o){
			case MAIN:
				txt.setText("Main Menu");
				break;
			case START:
				txt.setText("Start Game");
				break;
			case NEXT:
				txt.setText("Next Level");
				break;
			case EXIT:
				txt.setText("Exit Game");
				break;
			case HANDICAP_0:
				txt.setText("Easy HC:0");
				break;
			case HANDICAP_5:
				txt.setText("Medium HC:5");
				break;
			case HANDICAP_10:
				txt.setText("Hard HC:10");
				break;
		}
		txt.setStroke(Color.BLACK);
		txt.setStrokeWidth(2);
		this.getChildren().add(txt);
	}

	@Override
	protected Shape[] defineLayers() {
		Shape[] layers = new Shape[1];
		layers[0] = new Rectangle(360,60, Color.ORANGE);
		return layers;
	}

	@Override
	public void update() {

	}

	@Override
	public boolean collides() {
		return false;
	}
}
