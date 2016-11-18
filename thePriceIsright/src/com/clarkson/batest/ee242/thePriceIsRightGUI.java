package com.clarkson.batest.ee242;


import java.util.ArrayList;

import java.util.Random;
import java.util.Vector;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.shape.*;
import javafx.scene.text.TextAlignment;

/* Tony's notes. ***
 * 
 * All funtionality is complete.
 * 
 * Still to be Done:
 * 		
 * 		--WHAT DOES WHAT HANDS DO!?
 * 			-Javadocs
 * 		--.jar						<-Friday Problem
 * 		--submit.
 * 
 * @author tony	:3
 * 
 * 
 *
 */

/**
 *This is the first window the user sees. It sets up the stage and handles all the sceen
 *This creates all the Array lists that are used as the logic for the function.
 * @author Tony & Troy
 *
 */
public class thePriceIsRightGUI extends Application{
	
	//GameFunctionality engine = new GameFunctionality();
	/**
	 * Serves to launch the game
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	int numCards = 5;
	int Score = 0;
	/*
	 * Defines all the static variables accessed thought the program
	 */
	String cardsInHandRandom = new String();
	ArrayList<String> shapesInHand = new ArrayList<String>();
	ArrayList<String> colorsInHand = new ArrayList<String>();
	
	ArrayList<ImageView> cardBacks = new ArrayList<ImageView>();
	ArrayList<Rectangle> cardFront = new ArrayList<Rectangle>();
	ArrayList<StackPane> cards	   = new ArrayList<StackPane>();
	ArrayList<Shape> cardShape	 	= new ArrayList<Shape>();
	
	//GameFunctionality gameFunction	= new GameFunctionality();
	
	ArrayList<Color> chosenColors = new ArrayList<Color>();

	ArrayList<String> stringsColors = new ArrayList<String>();
	ArrayList<String> stringsShapes = new ArrayList<String>();
	
	protected static Random rand = new Random();
//	int numChoices[] = new int[]{3,5,7,9,11};
	
	int flipCounter=0;
	int roundCounter=0;
	/**
	 *Start is used to create the new game scene and lay out the root, takes in all the users input for the main game functionality
	 *
	 */
	@Override
	public void start(Stage primaryStage) {	// starts the "new game" window. this can be changed if necessary
		try {
			VBox root = new VBox();
			Scene newGame = new Scene(root);
			newGame.getStylesheets().add( 
					getClass().getResource("application.css").toExternalForm() );
			root.getStyleClass().add("backroundtheme");
			Insets insets = new Insets(10,10,10,10);

			Label welcome = new Label("Welcome to THE SHAPE IS RIGHT!!\n   ");
			welcome.getStyleClass().add("texttheme");
			Label ShapeMessage = new Label("Please Select how many shapes you would like to use:     ");
			ShapeMessage.getStyleClass().add("texttheme");
			ChoiceBox<String> selectShapeNum = new ChoiceBox<String>(
					FXCollections.observableArrayList(
				   "3","5","7", "9","11")
			);
			
			HBox numShapes = new HBox();
			numShapes.getChildren().addAll(ShapeMessage, selectShapeNum);

			HBox setup = new HBox();		//contains the game settings
			VBox Shapes = new VBox();
			VBox Colors = new VBox();

			Label whatShape = new Label("What Shapes Would You Like?");
			whatShape.getStyleClass().add("texttheme");
			ListView<String> shapePicker = new ListView<String> (FXCollections.observableArrayList(
					"Rectangle", "Circle", "Triangle", "Diamond"));	//These should be changed once shapes are finalized.
			shapePicker.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			Label whatColor = new Label("What Colors Would You Like?");
			whatColor.getStyleClass().add("texttheme");
			ListView<String> colorPicker = new ListView<String> (FXCollections.observableArrayList(
					"Blue", "Red", "Green", "Black"));	// these should also likely be changed.
			colorPicker.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			        
	        Button quitButton = new Button("Quit");
	        quitButton.getStyleClass().add("buttontheme");
	        quitButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {		
					
					Platform.exit();
				}
			});

			colorPicker.setPrefWidth(150.0);
			shapePicker.setPrefWidth(150.0);
			colorPicker.setPrefHeight((24 * 4) + 4);
			shapePicker.setPrefHeight((24 * 4) + 4);

			Shapes.getChildren().addAll(whatShape, shapePicker);	// need to set width/height to make it prettier
			Colors.getChildren().addAll(whatColor, colorPicker);	// ^ditto
			Shapes.setAlignment(Pos.CENTER);
			Colors.setAlignment(Pos.CENTER);
			setup.setAlignment(Pos.CENTER);
			Colors.setPadding(insets);
			Shapes.setPadding(insets);
			setup.getChildren().addAll(Shapes,Colors);

			Button beginBTN = new Button("BEGIN!!!!!!");
			beginBTN.getStyleClass().add("buttontheme");
			beginBTN.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {		
										
					int numberOfChosenColors = (colorPicker.getSelectionModel().getSelectedItems().size());
					int numberOfChosenShapes = (shapePicker.getSelectionModel().getSelectedItems().size());
						
					if(selectShapeNum.getSelectionModel().isEmpty()){
						numCards = 7;
					} else {
						numCards = Integer.parseInt(selectShapeNum.getSelectionModel().getSelectedItem());	
					}
						
					
					if(numberOfChosenColors == 0) {
						stringsColors.add("Black");
						stringsColors.add("Blue");
						stringsColors.add("Red");
						stringsColors.add("Green");
						chosenColors.add(Color.BLACK);
						chosenColors.add(Color.BLUE);
						chosenColors.add(Color.RED);
						chosenColors.add(Color.GREEN);
						
					} else {
						for(int i = 0; i<numberOfChosenColors;i++) {
							stringsColors.add(colorPicker.getSelectionModel().getSelectedItems().get(i));
						}
						
						for(int k = 0; k<numberOfChosenColors; k++){
							if(stringsColors.get(k)=="Black") {
								chosenColors.add(Color.BLACK);
							} else if(stringsColors.get(k)=="Blue") {
								chosenColors.add(Color.BLUE);
							} else if(stringsColors.get(k)=="Red") {
								chosenColors.add(Color.RED);
							} else if(stringsColors.get(k)=="Green") {
								chosenColors.add(Color.GREEN);
							}
						}
					}	
					
					if(numberOfChosenShapes == 0) {
						stringsShapes.add("Rectangle");
						stringsShapes.add("Triangle");
						stringsShapes.add("Circle");
						stringsShapes.add("Diamond");
						
					} else {
						for(int j = 0; j<numberOfChosenShapes;j++){
							stringsShapes.add(shapePicker.getSelectionModel().getSelectedItems().get(j));
						}
					}
					
					gameWindow(primaryStage);	// switches to game
					
				}
			});
			
			
			HBox buttons = new HBox(10);
			buttons.getChildren().addAll(beginBTN, quitButton);
			buttons.setAlignment(Pos.CENTER);
			root.getChildren().addAll(welcome, numShapes,setup, buttons);
			root.setAlignment(Pos.CENTER);
			root.setPadding(insets);
			primaryStage.initStyle( StageStyle.UTILITY  );	
			primaryStage.setTitle("The Shapes are Right!");
			primaryStage.setScene(newGame);
			primaryStage.sizeToScene();
			primaryStage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * This provides the main game functionality to dynamically load in random cards and then track how the user guesses
 * @param stage
 */
	public void gameWindow(Stage stage) {	// should probably be moved to it's own file.
		try {
			VBox organizer = new VBox();		// I'm not sure how to safely make a file in git, so this is best for now
			Scene Game = new Scene(organizer);
			Game.getStylesheets().add( 
					getClass().getResource("application.css").toExternalForm() );
			organizer.getStyleClass().add("backroundtheme");
			Label title = new Label("The Shapes Are Right!!!!");
			title.getStyleClass().add("titletheme");
			//title.setTextAlignment(TextAlignment.CENTER);

//Holds the cards
			HBox cardArea = new HBox(10);

			//Troy's edits
			
			cardShape=newHand();
			for( int cardCount =0; cardCount < numCards; cardCount++){
				cardFront.add(new Rectangle(100.0,150.0));
				cardFront.get(cardCount).setFill(Color.WHITE);
				cardBacks.add(new ImageView());
				cardBacks.get(cardCount).setImage(new Image(getClass().getResource("cardBackImage.jpg").toExternalForm()));
				cards.add(new StackPane());			
				cards.get(cardCount).getChildren().add(cardFront.get(cardCount));
				cards.get(cardCount).getChildren().add(cardShape.get(cardCount));
				cards.get(cardCount).getChildren().add(cardBacks.get(cardCount));
				
			}
			cardArea.getChildren().addAll(cards);
			
			
//Card Color Guess
			ListView<String> CardColorGuess = new ListView<String> (
					FXCollections.observableArrayList(stringsColors));

			CardColorGuess.setPrefHeight((24*4)+4);
			CardColorGuess.setPrefWidth(125);
			
//card Shape Guess
			ListView<String> CardShapeGuess = new ListView<String>(FXCollections.observableArrayList(stringsShapes));

			CardShapeGuess.setPrefHeight((24*4)+4);
			CardShapeGuess.setPrefWidth(125);

//score counter and current shapes			
			Label currentShapes = new Label("Cards Dealt:\n" + cardsInHandRandom);
			currentShapes.getStyleClass().add("texttheme");
			currentShapes.setTextAlignment(TextAlignment.CENTER);
			Label currentScore = new Label("Score: " + Score);
			currentScore.getStyleClass().add("texttheme");
//Holds controls and Stats
			HBox ControlArea = new HBox(10);
			ControlArea.setPadding(new Insets(10,10,10,10));
			ControlArea.getChildren().addAll(currentScore,CardShapeGuess, CardColorGuess,currentShapes);
			ControlArea.setAlignment(Pos.CENTER);

			cardArea.setPadding(new Insets(10,10,10,10));
			cardArea.setAlignment(Pos.CENTER);

//Label to say to guess
			Label guess = new Label("Guess the Next Shape and Color!!");
			guess.getStyleClass().add("texttheme");
			
//data for flip

		
			
//Flip Next Card Button
			Button flipNext = new Button("Flip Next Card!");
			flipNext.getStyleClass().add("buttontheme");
			flipNext.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
				
					if (flipCounter < numCards){
						FlipCardOver(cards.get(flipCounter));
//						cardBacks.get(flipCounter).setVisible(false);	// needs changed to cutesy animation				
		
		//Calculates Score
						
						if(CardColorGuess.getSelectionModel().isEmpty()|| CardShapeGuess.getSelectionModel().isEmpty()){
							//nothing. no points can be earned.
						} else {
							String ColorGuess = new String();
							ColorGuess=CardColorGuess.getSelectionModel().getSelectedItem();
							String ShapeGuess = new String();
							ShapeGuess =CardShapeGuess.getSelectionModel().getSelectedItem();
							
							if (ColorGuess.equals(colorsInHand.get(flipCounter))){
								if(ShapeGuess.equals(shapesInHand.get(flipCounter))){
					        		Score = Score + 1;
					        		currentScore.setText("Score: " + Score);
					        	}
						    }
						}
						
						flipCounter++;
		//End of round/Game
					if (flipCounter==numCards){
							flipNext.setText("Next Round!!");
							roundCounter = roundCounter +1;
						}
					
					}

					else{		//RESETS FOR NEXT ROUND		
						flipCounter=0;
						if(roundCounter == 3) {
							gameOver(stage);
						}
						else {
						flipNext.setText("Flip Next Card!");
						flipNext.setDisable(true);
						cardShape.clear();
						colorsInHand.clear();
						shapesInHand.clear();
						cardsInHandRandom = "";

						cardShape=newHand();
			//Rebuilds Hand
						cardArea.getChildren().clear();
						cards.clear();
						
						for( int cardCount =0; cardCount < numCards; cardCount++){							
							cards.add(new StackPane());			
							cards.get(cardCount).getChildren().add(cardFront.get(cardCount));
							cards.get(cardCount).getChildren().add(cardShape.get(cardCount));
							cards.get(cardCount).getChildren().add(cardBacks.get(cardCount));
							FlipCardBack(cards.get(cardCount));
							cardBacks.get(cardCount).setOpacity(1.0);
						}
						
						cardArea.getChildren().addAll(cards);
						currentShapes.setText("Cards Dealt:\n" + cardsInHandRandom);
						
						flipNext.setDisable(false);
						}

					}
				}
			});

//Quits the Game
			 Button quitButton = new Button("Quit");
			 quitButton.getStyleClass().add("buttontheme");
		        quitButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {		
						
						Platform.exit();
					}
				});
	
//sets up formatting
		    HBox Buttons = new HBox(10);
		    Buttons.getChildren().addAll(flipNext, quitButton);
		    Buttons.setAlignment(Pos.CENTER);

			organizer.getChildren().addAll(title, cardArea,guess, ControlArea, Buttons);

			//organizer.getChildren().add(actualCard.returnShape());

			organizer.setPadding(new Insets(10,10,10,10));
			organizer.setAlignment(Pos.CENTER);
			stage.sizeToScene();
			stage.setScene(Game);
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	ArrayList<Shape> newHand(){
		ArrayList<Shape> cardList= new ArrayList<Shape>();
		Shape tempShape;
	//	Color tempColor;
		for(int count = 0; count < numCards; count++){
			int tempChoice = rand.nextInt(stringsShapes.size());
	//		tempShape = chosenShapes.get(tempChoice);
			if(stringsShapes.get(tempChoice).equals("Diamond")){
				tempShape = new Rectangle(50.0, 50.0);
				tempShape.setRotate(45.0);
			
			}
			else if(stringsShapes.get(tempChoice).equals("Rectangle")){
				tempShape = new Rectangle(50.0, 50.0);
				tempShape.setRotate(360.0);
			
			}
			else if (stringsShapes.get(tempChoice).equals("Triangle")){
				tempShape = new Polygon(25.0, 0.0, 50.0, 50.0, 0.0, 50.0);
				tempShape.setRotate(720.0);
				
			}
			else if( stringsShapes.get(tempChoice).equals("Circle")){
				tempShape = new Circle(27.0);
				tempShape.setRotate(1080.0);	// makes it so i can tell what it is
				
			} else {
				tempShape = new QuadCurve();
			}
			tempChoice = rand.nextInt(chosenColors.size());
			tempShape.setFill(chosenColors.get(tempChoice));
			cardList.add(tempShape);
		}
		
		whatCards(cardList);	// deleting this line here will ruin EVERYTHING.
		return cardList;
	}

	public void whatCards(ArrayList<Shape> hand) {
		
		Vector<String> currentCards = new Vector<String>();
				
		for(int i = 0; i <numCards; i++) {	//generates array of strings rep'ing the current hand.
			Shape current = hand.get(i);
			String currentString = new String();
			if(current.getFill() == Color.BLACK) {
				currentString = "Black ";
				colorsInHand.add("Black");
			} else if (current.getFill() == Color.RED) {
				currentString = "Red ";
				colorsInHand.add("Red");
			} else if (current.getFill() == Color.BLUE) {
				currentString = "Blue ";
				colorsInHand.add("Blue");
			} else if (current.getFill() == Color.GREEN) {
				currentString = "Green ";
				colorsInHand.add("Green");
			}
			
			if(current.getRotate() == 45.0) {	// diamond
				currentString += " Diamond\n";
				shapesInHand.add("Diamond");
			} else if ( current.getRotate() == 1080.0){		
				currentString += "Circle\n";
				shapesInHand.add("Circle");
			} else if ( current.getRotate() == 360.0) {
				currentString += "Rectangle\n";
				shapesInHand.add("Rectangle");
			} else if ( current.getRotate() == 720.0) {
				currentString += "Triangle\n";
				shapesInHand.add("Triangle");
			}
			currentCards.add(currentString);
		}
		for(int j = 0; j<numCards; j++ ) {	//generates String randomizing what cards are in the hand
			int tempIndex = rand.nextInt(numCards-j);
			cardsInHandRandom += currentCards.remove(tempIndex);
		}
	}
/**
 * Game over loads the scene to ask the user if they would like to play again and display their score.
 * If the user says quit the application is exited.
 * Otherwise the user is taken back to the first window and is shown options to play agian.
 * @param stage
 */
	public void gameOver(Stage stage) {
		
		VBox organizer = new VBox(10);
		Scene GameOver = new Scene(organizer);
		GameOver.getStylesheets().add( 
				getClass().getResource("application.css").toExternalForm() );
		organizer.getStyleClass().add("backroundtheme");
		Label title = new Label("Game Over!\n Don't Forget To Get Your Pets Spayed or Neutered!!");
		title.setTextAlignment(TextAlignment.CENTER);
		title.getStyleClass().add("texttheme");
		Label finalScore = new Label("Final Score: " + Score);
		finalScore.getStyleClass().add("texttheme");
		Label message = new Label("What Would You Like to Do?");
		message.getStyleClass().add("texttheme");
		message.setTextAlignment(TextAlignment.CENTER);
		
        Button quitButton = new Button("Quit");
        quitButton.getStyleClass().add("buttontheme");
        quitButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {		
				
				Platform.exit();
			}
		});
        
        Button newGame = new Button("New Game!");		//TOTAL RESET.
        newGame.getStyleClass().add("buttontheme");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		numCards = 0;
        		Score = 0;
        		cardsInHandRandom = "";
        		shapesInHand.clear();
        		colorsInHand.clear();
        		cardBacks.clear();
        		cardFront.clear();
        		cards.clear();
        		cardShape.clear();
        		chosenColors.clear();
        		stringsColors.clear();
        		stringsShapes.clear();
        		flipCounter=0;
        		roundCounter=0;
        		
        		
        		
        		start(new Stage());
        		
        		stage.close();
        	}
        });
		
		HBox Buttons = new HBox(10);
		Buttons.getChildren().addAll(newGame, quitButton);
		
		organizer.setAlignment(Pos.CENTER);
		Buttons.setAlignment(Pos.CENTER);
		message.setTextAlignment(TextAlignment.CENTER);
		organizer.setPadding(new Insets(10,10,10,10));
		
		organizer.getChildren().addAll(title, finalScore, message, Buttons);
		stage.setScene(GameOver);
		stage.sizeToScene();
		stage.show();
		
		
	}
/**
 * Provides the animations to flip a face down card over. This is given a stack pane, which represents a card.
 * @param fullCard
 */
	void FlipCardOver(StackPane fullCard){
		Property translateXProperty = fullCard.translateXProperty();
		Property translateScalProperty = fullCard.scaleXProperty();
		Property transperencyProperty =fullCard.getChildren().get(2).opacityProperty();
	            new FadeTransition(Duration.millis(1),fullCard.getChildren().get(1) );
		KeyFrame keyFrame0 = new KeyFrame(
				Duration.ZERO,
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( translateScalProperty ,1.0 ),
				new KeyValue( transperencyProperty, 1.0));
		KeyFrame keyFrame500 = new KeyFrame(
				new Duration( 500 ),
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( translateScalProperty ,0.0 ),
				new KeyValue( transperencyProperty, 1.0));
		KeyFrame keyFrame501 = new KeyFrame(
				new Duration( 501 ),
				new KeyValue( translateXProperty,00.0 ),
				new KeyValue( translateScalProperty ,0.0 ),
				new KeyValue( transperencyProperty, 0.0));
		KeyFrame keyFrame1000 = new KeyFrame(
				new Duration( 1000 ),
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( translateScalProperty ,1.0 ),
				new KeyValue( transperencyProperty, 0.0));
		
		
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add( keyFrame0 );
		timeline.getKeyFrames().add( keyFrame500 );
		timeline.getKeyFrames().add(keyFrame501);
		timeline.getKeyFrames().add( keyFrame1000 );
		timeline.play();
	}
	/**
	 * Provides the animations to flip a face up card down. This is given a stack pane, which represents a card.
	 * @param fullCard
	 */
	void FlipCardBack(StackPane fullCard){
		Property translateXProperty = fullCard.translateXProperty();
		Property translateScalProperty = fullCard.scaleXProperty();
		Property transperencyProperty =fullCard.getChildren().get(2).opacityProperty();
		Property transperencyShapeProperty =fullCard.getChildren().get(1).opacityProperty();
	            new FadeTransition(Duration.millis(1),fullCard.getChildren().get(1) );
		KeyFrame keyFrame0 = new KeyFrame(
				Duration.ZERO,
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( translateScalProperty ,1.0 ),
				new KeyValue( transperencyShapeProperty, 0.0),
				new KeyValue( transperencyProperty, 0.0));
		KeyFrame keyFrame100 = new KeyFrame(
				new Duration( 100 ),
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( translateScalProperty ,0.0 ),
				new KeyValue( transperencyShapeProperty, 0.0),
				new KeyValue( transperencyProperty, 0.0));
		KeyFrame keyFrame101 = new KeyFrame(
				new Duration( 101 ),
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( translateScalProperty ,0.0 ),
				new KeyValue( transperencyShapeProperty, 1.0),
				new KeyValue( transperencyProperty, 1.0));
		KeyFrame keyFrame200 = new KeyFrame(
				new Duration( 200 ),
				new KeyValue( translateXProperty,0.0 ),
				new KeyValue( transperencyShapeProperty, 1.0),
				new KeyValue( translateScalProperty ,1.0 ),
				new KeyValue( transperencyProperty, 1.0));
		
		
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add( keyFrame0 );
		timeline.getKeyFrames().add( keyFrame100 );
		timeline.getKeyFrames().add(keyFrame101);
		timeline.getKeyFrames().add( keyFrame200 );
		timeline.play();
	}
	
	
}
