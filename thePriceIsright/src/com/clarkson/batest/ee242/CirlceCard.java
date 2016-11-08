package com.clarkson.batest.ee242;

import java.awt.Color;
import javafx.scene.shape.Circle;


public class CirlceCard extends Card{
	
	CirlceCard(){
		this(false, Color.BLACK);
	}
	CirlceCard(Boolean flip, Color newColor){
		flipped = flip;
		color=newColor;
		shape = new Circle();
	}
}
