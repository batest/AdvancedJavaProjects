package com.clarkson.batest.ee242;

import java.awt.Color;
import javafx.scene.shape.*;
/**
 * This is the generic class for all of our cards
 * @author tony
 *
 */
public class Card {
	protected Boolean flipped;
	protected Color color;
	protected Shape shape;
	Card(){
		this(false, Color.BLACK, new Rectangle());
	}
	Card(Boolean setfliped, Color setcolor, Shape setShape){
		flipped=setfliped;
		color = setcolor;
		shape=setShape;
	}
	void setColor(Color newColor){
		color = newColor;
	}
	Color returnColor(){
		return color;
	}
	Boolean filpCard(){
		return flipped=!flipped;
	}
	void setShape(Shape newShape){
		shape=newShape;
	}
	Shape returnShape(){
		return shape;
	}
}