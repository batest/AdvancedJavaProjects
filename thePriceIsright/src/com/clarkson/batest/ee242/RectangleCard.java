package com.clarkson.batest.ee242;

import java.awt.Color;
import javafx.scene.shape.*;


public class RectangleCard extends Card{
	RectangleCard(){
		this(false, Color.BLACK);
	}
	RectangleCard(Boolean flip, Color newColor){
		flipped = flip;
		color=newColor;
		shape = new Rectangle();
	}
}
