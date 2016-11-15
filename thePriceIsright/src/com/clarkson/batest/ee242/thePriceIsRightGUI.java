package com.clarkson.batest.ee242;

//import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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


/* TONY'S NOTES

*/

/*Troy's notes
 * fix new hand hand functionality  
 * create string based on what cards are available.
 * 
 * @author troy
 *
 */
public class thePriceIsRightGUI extends Application{
	
	GameFunctionality engine = new GameFunctionality();
	
	public static void main(String[] args) {
		launch(args);
	}

	int numCards = 7;
	int Score = 0;
	String cardsInHand = new String();
	ArrayList<ImageView> cardBacks = new ArrayList<ImageView>();
	ArrayList<Rectangle> cardFront = new ArrayList<Rectangle>();
	ArrayList<StackPane> cards	   = new ArrayList<StackPane>();
	ArrayList<Shape> cardShape	 	= new ArrayList<Shape>();
	GameFunctionality gameFunction	= new GameFunctionality();
	ArrayList<Color> chosenColors = new ArrayList<Color>();
//	ArrayList<Shape> chosenShapes = new ArrayList<Shape>();	
	ArrayList<String> stringsColors = new ArrayList<String>();
	ArrayList<String> stringsShapes = new ArrayList<String>();
	protected static Random rand = new Random();
	int numChoices[] = new int[]{3,5,7};
	
	int flipCounter=0;
	@Override
	public void start(Stage primaryStage) {	// starts the "new game" window. this can be changed if necessary
		try {
			VBox root = new VBox();
			Scene newGame = new Scene(root);
			newGame.getStylesheets().add( 
					getClass().getResource("application.css").toExternalForm() );
			
			Insets insets = new Insets(10,10,10,10);

			Label welcome = new Label("Welcome to THE SHAPE IS RIGHT!!\n   ");
			Label ShapeMessage = new Label("Please Select how many shapes you would like to use:     ");

			ChoiceBox<String> selectShapeNum = new ChoiceBox<String>(
					FXCollections.observableArrayList(
				   "3","5","7")
			);
			
			selectShapeNum.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener <Number> () {
					public void changed(ObservableValue ov, Number value, Number new_value) {
						numCards = numChoices[new_value.intValue()];
					}
			});
			

			HBox numShapes = new HBox();
			numShapes.getChildren().addAll(ShapeMessage, selectShapeNum);

			HBox setup = new HBox();		//contains the game settings
			VBox Shapes = new VBox();
			VBox Colors = new VBox();

			Label whatShape = new Label("What Shapes Would You Like?");
			ListView<String> shapePicker = new ListView<String> (FXCollections.observableArrayList(
					"Rectangle", "Circle", "Triangle", "Diamond"));	//These should be changed once shapes are finalized.
			shapePicker.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
	        shapePicker.getSelectionModel().getSelectedItems().addListener(
	        		new ListChangeListener<String>() {
						@Override
						public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
							
							c.next();
							if(c.wasAdded()){
								
								List<? extends String> added = c.getAddedSubList();
								for (int i = 0; i<added.size(); i++) {
									if(added.get(i) == "Rectangle") {
//										chosenShapes.add(new Rectangle(50.0, 50.0));
										stringsShapes.add("Rectangle");
									} else if (added.get(i) == "Circle") {
//										chosenShapes.add(new Circle(25.0));
										stringsShapes.add("Circle");
									}else if (added.get(i) == "Triangle") {
//										chosenShapes.add(new Polygon(25.0, 0.0, 50.0, 50.0, 0.0, 50.0)); 
										stringsShapes.add("Triangle");
									} else if (added.get(i) == "Diamond") {
//										Shape tempShape =new Rectangle(50.0, 50.0);
//										tempShape.setRotate(45.0);
//										chosenShapes.add(tempShape);
										stringsShapes.add("Diamond");
									}
									
								}
																
								
							} if (c.wasRemoved()) {
								
								List<? extends String> removed = c.getRemoved();
								for (int i = 0; i<removed.size(); i++) {
									if(removed.get(i) == "Rectangle") {
										stringsShapes.remove("Rectangle");
									} else if (removed.get(i) == "Circle") {
										stringsShapes.remove("Circle");
									} else if (removed.get(i) == "Triangle") {
										stringsShapes.remove("Triangle");
									} else if (removed.get(i) == "Diamond") {
										stringsShapes.remove("Diamond");
									} else {
										//bad things happened.
										break;
									}
								}
							}	
						}
	        	
	        });
			
			Label whatColor = new Label("What Colors Would You Like?");
			ListView<String> colorPicker = new ListView<String> (FXCollections.observableArrayList(
					"Blue", "Red", "Green", "Black"));	// these should also likely be changed.
			colorPicker.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
	        colorPicker.getSelectionModel().getSelectedItems().addListener(
	        		new ListChangeListener<String>() {
						@Override
						public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c) {
							c.next();
							if(c.wasAdded()){
								
								List<? extends String> added = c.getAddedSubList();
								for (int i = 0; i<added.size(); i++) {
									if(added.get(i) == "Blue") {
										chosenColors.add(Color.BLUE);
										stringsColors.add("Blue");
									} else if (added.get(i) == "Red") {
										chosenColors.add(Color.RED);
										stringsColors.add("Red");
									} else if (added.get(i) == "Green") {
										chosenColors.add(Color.GREEN);
										stringsColors.add("Green");
									} else if (added.get(i) == "Black") {
										chosenColors.add(Color.BLACK);
										stringsColors.add("Black");
									} else {
										//bad things happened.
										break;
									}
								}
																
								
							} if (c.wasRemoved()) {
								
								List<? extends String> removed = c.getRemoved();
								for (int i = 0; i<removed.size(); i++) {
									if(removed.get(i) == "Blue") {
										chosenColors.remove(Color.BLUE);
									} else if (removed.get(i) == "Red") {
										chosenColors.remove(Color.RED);
									} else if (removed.get(i) == "Green") {
										chosenColors.remove(Color.GREEN);
									} else if (removed.get(i) == "Black") {
										chosenColors.remove(Color.BLACK);
									} else {
										//bad things happened.
										break;
									}
								}
							}	
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
			beginBTN.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {		
					
					checkStringArrays();
					
					gameWindow(primaryStage);	// switches to game

				}
			});

			root.getChildren().addAll(welcome, numShapes,setup, beginBTN);
			root.setAlignment(Pos.CENTER);
			root.setPadding(insets);

			primaryStage.setScene(newGame);
			primaryStage.sizeToScene();
			primaryStage.show();
		}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void gameWindow(Stage stage) {	// should probably be moved to it's own file.
		try {
			VBox organizer = new VBox();		// I'm not sure how to safely make a file in git, so this is best for now
			Scene Game = new Scene(organizer);

			Label title = new Label("The Shapes Are Right!!!!\n Don't forget to get your pets spayed or neutered!!");
			title.setTextAlignment(TextAlignment.CENTER);
			
			HBox cardArea = new HBox(10);

			//Troy's edits
			
			cardShape=newHand();
			for( int cardCount =0; cardCount < numCards; cardCount++){
				cardFront.add(new Rectangle(100.0,150.0));
				cardFront.get(cardCount).setFill(Color.WHITE);
				cardBacks.add(new ImageView());
				cardBacks.get(cardCount).setImage(new Image(getClass().getResource("cardBackImage.jpg").toExternalForm()));
				//cardShape.get(cardCount).setRotate(45.0);	// needs to add randomization, but nice.
				//cardShape.get(cardCount).setWidth(50.0);
				//add in card objects; square triangles ...
				cards.add(new StackPane());			
				cards.get(cardCount).getChildren().add(cardFront.get(cardCount));
				cards.get(cardCount).getChildren().add(cardShape.get(cardCount));
				cards.get(cardCount).getChildren().add(cardBacks.get(cardCount));
				
			}
			cardArea.getChildren().addAll(cards);
			
			RectangleCard actualCard = new RectangleCard();

			ListView<String> CardColorGuess = new ListView<String> (
					FXCollections.observableArrayList(stringsColors));

			CardColorGuess.setPrefHeight((24*4)+4);
			CardColorGuess.setPrefWidth(125);

			ListView<String> CardShapeGuess = new ListView<String>(FXCollections.observableArrayList(stringsShapes));

			CardShapeGuess.setPrefHeight((24*4)+4);
			CardShapeGuess.setPrefWidth(125);

			//score counter and current shapes			
			Label currentShapes = new Label("Cards Dealt:\n" + cardsInHand);
			currentShapes.setTextAlignment(TextAlignment.CENTER);
			Label currentScore = new Label("Score: " + Score);

			
			HBox ControlArea = new HBox(10);
			ControlArea.setPadding(new Insets(10,10,10,10));
			ControlArea.getChildren().addAll(currentScore,CardShapeGuess, CardColorGuess,currentShapes);
			ControlArea.setAlignment(Pos.CENTER);

			cardArea.setPadding(new Insets(10,10,10,10));
			cardArea.setAlignment(Pos.CENTER);

			Label guess = new Label("Guess the Next Shape and Color!!");

			Button flipNext = new Button("Flip Next Card!");
			flipNext.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (flipCounter < numCards){
						cardBacks.get(flipCounter).setVisible(false);	// needs changed to cutesy animation				
						flipCounter++;
						
						if (flipCounter==numCards){
							flipNext.setText("New Game?");
						}
					}
					else{
						//System.out.println("hey listen");
						
							flipCounter=0;
						
						cardShape=newHand();
						for(int tempCount=0; tempCount < numCards; tempCount++){
							//cards.get(tempCount).getChildren().set(0, cardShape.get(tempCount));
							cardBacks.get(tempCount).setVisible(true);
						}
						
					
						
						
						flipNext.setText("Flip Next Card!");
					}
					//with the array this will be able to change an index of the array, too

				}
			});

			organizer.getChildren().addAll(title, cardArea,guess, ControlArea, flipNext);

			organizer.getChildren().add(actualCard.returnShape());

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
				tempShape.setRotate(90);
			
			}
			else if (stringsShapes.get(tempChoice).equals("Triangle")){
				tempShape = new Polygon(25.0, 0.0, 50.0, 50.0, 0.0, 50.0);
				tempShape.setRotate(360);
				
			}
			else if( stringsShapes.get(tempChoice).equals("Circle")){
				tempShape = new Circle(27.0);
				tempShape.setRotate(42.0);	// makes it so i can tell what it is
				
			} else {
				tempShape = new QuadCurve();
			}
			tempChoice = rand.nextInt(chosenColors.size());
			tempShape.setFill(chosenColors.get(tempChoice));
			cardList.add(tempShape);
		}
		
		whatCards(cardList);
		
		return cardList;
	}
	
	public void whatCards(ArrayList<Shape> hand) {
		
		Vector<String> currentCards = new Vector<String>();
		
		for(int i = 0; i <numCards; i++) {	//generates array of strings rep'ing the current hand.
			Shape current = hand.get(i);
			String currentString = new String();
			if(current.getFill() == Color.BLACK) {
				currentString = "Black ";
			} else if (current.getFill() == Color.RED) {
				currentString = "Red ";
			} else if (current.getFill() == Color.BLUE) {
				currentString = "Blue ";
			} else if (current.getFill() == Color.GREEN) {
				currentString = "Green ";
			}
			
			if(current.getRotate() == 45.0) {	// diamond
				currentString += " Diamond\n";
			} else if ( current.getRotate() == 42.0){		
				currentString += "Circle\n";
			} else if ( current.getRotate() == 90.0) {
				currentString += "Rectangle\n";
			} else if ( current.getRotate() == 360.0) {
				currentString += "Triangle\n";
			}
			currentCards.add(currentString);
		}
		for(int j = 0; j<numCards; j++ ) {	//generates String randomizing what cards are in the hand
			int tempIndex = rand.nextInt(numCards-j);
			cardsInHand += currentCards.remove(tempIndex);
		}
	}
	
	public void checkStringArrays() {	// Brute checks for duplicates or sets defaults
		
		if(stringsShapes.isEmpty() || stringsColors.isEmpty()) {
			if(stringsShapes.isEmpty()) {
				stringsShapes.add("Rectangle");
				stringsShapes.add("Circle");
				stringsShapes.add("Triangle");
				stringsShapes.add("Diamond");
				}
			if(stringsColors.isEmpty()) {
				stringsColors.add("Green");
				stringsColors.add("Black");
				stringsColors.add("Red");
				stringsColors.add("Blue");
			}if(chosenColors.isEmpty()){
				chosenColors.add(Color.BLACK);
				chosenColors.add(Color.GREEN);
				chosenColors.add(Color.RED);
				chosenColors.add(Color.BLUE);
			}			
			return;
		}
		
		
		if(stringsColors.contains("Black")) {
			int count = 0;
			for(int i = 0; i < stringsColors.size(); i++) {
				if(stringsColors.get(i) == "Black") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsColors.size(); j++) {
					if(stringsColors.get(j) == "Black") {
						stringsColors.remove(j);
					}
				}
			}
			
		}
		if(stringsColors.contains("Blue")) {
			int count = 0;
			for(int i = 0; i < stringsColors.size(); i++) {
				if(stringsColors.get(i) == "Blue") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsColors.size(); j++) {
					if(stringsColors.get(j) == "Blue") {
						stringsColors.remove(j);
					}
				}
			}
			
		}
		if(stringsColors.contains("Red")) {
			int count = 0;
			for(int i = 0; i < stringsColors.size(); i++) {
				if(stringsColors.get(i) == "Red") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsColors.size(); j++) {
					if(stringsColors.get(j) == "Red") {
						stringsColors.remove(j);
					}
				}
			}
			
		}
		if(stringsColors.contains("Green")) {
			int count = 0;
			for(int i = 0; i < stringsColors.size(); i++) {
				if(stringsColors.get(i) == "Green") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsColors.size(); j++) {
					if(stringsColors.get(j) == "Green") {
						stringsColors.remove(j);
					}
				}
			}
			
		}
		if(stringsShapes.contains("Rectangle")) {
			int count = 0;
			for(int i = 0; i < stringsShapes.size(); i++) {
				if(stringsShapes.get(i) == "Rectangle") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsShapes.size(); j++) {
					if(stringsShapes.get(j) == "Rectangle") {
						stringsShapes.remove(j);
					}
				}
			}	
		}
		if(stringsShapes.contains("Diamond")) {
			int count = 0;
			for(int i = 0; i < stringsShapes.size(); i++) {
				if(stringsShapes.get(i) == "Diamond") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsShapes.size(); j++) {
					if(stringsShapes.get(j) == "Diamond") {
						stringsShapes.remove(j);
					}
				}
			}	
		}
		if(stringsShapes.contains("Circle")) {
			int count = 0;
			for(int i = 0; i < stringsShapes.size(); i++) {
				if(stringsShapes.get(i) == "Circle") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsShapes.size(); j++) {
					if(stringsShapes.get(j) == "Circle") {
						stringsShapes.remove(j);
					}
				}
			}	
		}
		if(stringsShapes.contains("Triangle")) {
			int count = 0;
			for(int i = 0; i < stringsShapes.size(); i++) {
				if(stringsShapes.get(i) == "Triangle") {count++;}
			}
			if(count > 1) {
				for(int j = 0; j < stringsShapes.size(); j++) {
					if(stringsShapes.get(j) == "Triangle") {
						stringsShapes.remove(j);
					}
				}
			}	
		}
		
	}

	
}