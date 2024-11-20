package com.thu_cg_proj2d_engolfed.buttons;

import com.thu_cg_proj2d_engolfed.gameobjects.GameObject;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public abstract class Button extends GameObject {
	Text txt;


	public Button(double x, double y){
		super(x,y);
		initText();
		setOnMouseClicked(this::setEffect);
	}

	private void setEffect(MouseEvent mouseEvent) {
	}

	private void initText() {
		this.txt = new Text();
		txt.setStyle("-fx-font-weight: bold; -fx-font-family: 'Courier New'; -fx-font-size: 16px;" +
				"-fx-alignment: center; -fx-text-fill: white;");
		txt.setTranslateX(180);
		txt.setTranslateY(40);
	}

	@Override
	protected Shape[] defineLayers() {
		Shape[] layers = new Shape[1];
		layers[0] = new Rectangle(360,60, Color.ORANGE);
		return layers;
	}

	public void setTxt(String txt) {
		this.txt.setText(txt);
	}

	@Override
	public void update() {

	}

}
