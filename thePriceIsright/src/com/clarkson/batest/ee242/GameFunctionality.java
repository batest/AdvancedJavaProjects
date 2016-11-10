package com.clarkson.batest.ee242;

import java.util.Random;
import java.util.Vector;
import java.awt.Color;

public class GameFunctionality {
	
	private static int numberOfCards=0;
	protected static Random rand = new Random();
	public Vector<Card> cardVector = new Vector<Card>();
	
	static void main() {
		//start GUI
	};
	static void setNumberOfCards(int cardCount){
		numberOfCards=cardCount;
		//start new game
	}
	void createHand(){
		cardVector.clear();
		for(int count = 0; count < numberOfCards; count++){
			int tempChoice = rand.nextInt(4);
			switch(tempChoice){
				case 1:
					cardVector.add(new RectangleCard());
				case 2:
					cardVector.add(new CirlceCard());
				case 3:
					cardVector.add(new TriangleCard());
				case 4:
					//cardVector.add(new DiamondCard());
			}
			tempChoice = rand.nextInt(4);
			Color tempColor = Color.BLUE;;
			switch(tempChoice){
			case 1:
				tempColor=Color.BLUE;
			case 2:
				tempColor=Color.RED;
			case 3:
				tempColor=Color.GREEN;
			case 4:
				tempColor=Color.BLACK;
			}
			cardVector.get(count).setColor(tempColor);
		}
	}
	void flipCard( int index){
		cardVector.get(index).filpCard();
	}
}
