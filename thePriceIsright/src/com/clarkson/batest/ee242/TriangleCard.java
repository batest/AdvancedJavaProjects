package com.clarkson.batest.ee242;

import java.awt.Color;

import javafx.scene.shape.Rectangle;



public class TriangleCard extends Card{
	TriangleCard(){
		this(false, Color.BLACK);
	}
	TriangleCard(Boolean flip, Color newColor){
		flipped = flip;
		color=newColor;
		shape = new Rectangle();
	}	
}
