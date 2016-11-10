package com.clarkson.batest.ee242;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.shape.*;

import java.awt.Color;

public class GameFunctionality {
	
	private static int numberOfCards=0;
	protected static Random rand = new Random();
	public ArrayList<Card> cardList = new ArrayList<Card>();
	private ArrayList<Color> listOfColors = new ArrayList<Color>();
	private ArrayList<Shape> listOfShapes = new ArrayList<Shape>();
	private ArrayList<Color> listOfUserColors = new ArrayList<Color>();
	private ArrayList<Shape> listOfUserShapes = new ArrayList<Shape>();
	static void main() {
		//start GUI
	};
	static void setNumberOfCards(int cardCount){
		numberOfCards=cardCount;
		//start new game
	}
	
	void startGame(){
		listOfColors.add(Color.BLUE);
		listOfColors.add(Color.BLACK);
		listOfColors.add(Color.GREEN);
		listOfColors.add(Color.RED);
		listOfShapes.add(new Rectangle());
		listOfShapes.add(new Circle());
		listOfShapes.add(new Rectangle());
		//listOfShapes.add(new Triangle());
	}
	/**
	 * Function takes in three inputs The user desired cards, shapes, and number of cards
	 * I then return an array of cards with set parameters
	 * @param userSelectedColor
	 * @param userSelectedShapes
	 * @param numberOfCards
	 */
	ArrayList<Card> generateHand(ArrayList<Color> userSelectedColor, ArrayList <Shape> userSelectedShapes, int usersNumberOfCards){
		cardList.clear();
		numberOfCards=usersNumberOfCards;
		listOfUserColors = userSelectedColor;
		listOfUserShapes = userSelectedShapes;
		Shape tempShape;
		Color tempColor;
		for(int count = 0; count < usersNumberOfCards; count++){
			int tempChoice = rand.nextInt(userSelectedColor.size());
			tempColor = userSelectedColor.get(tempChoice);
			tempChoice = rand.nextInt(userSelectedShapes.size());
			tempShape = userSelectedShapes.get(tempChoice);
			cardList.add( new Card(false, tempColor, tempShape));
		}
		return cardList;
	}
	ArrayList<Card> newHand(){
		Shape tempShape;
		Color tempColor;
		for(int count = 0; count < numberOfCards; count++){
			int tempChoice = rand.nextInt(listOfUserColors.size());
			tempColor = listOfUserColors.get(tempChoice);
			tempChoice = rand.nextInt(listOfUserShapes.size());
			tempShape = listOfUserShapes.get(tempChoice);
			cardList.add( new Card(false, tempColor, tempShape));
		}
		return cardList;
	}
	
	void flipCard( int index){
		cardList.get(index).filpCard();
	}
}