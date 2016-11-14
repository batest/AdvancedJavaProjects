package com.clarkson.batest.ee242;

//import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;


/* TONY'S NOTES

*/

public class thePriceIsRightGUI extends Application{
	
	GameFunctionality engine = new GameFunctionality();
	
	public static void main(String[] args) {
		launch(args);
	}

	int numCards = 7;		//Placeholder variable for my testing.
	ArrayList<ImageView> cardBacks = new ArrayList<ImageView>();
	ArrayList<Rectangle> cardFront = new ArrayList<Rectangle>();
	ArrayList<StackPane> cards	   = new ArrayList<StackPane>();
	
	ArrayList<Color> chosenColors = new ArrayList<Color>();
	ArrayList<Shape> chosenShapes = new ArrayList<Shape>();	
	ArrayList<String> stringsColors = new ArrayList<String>();
	ArrayList<String> stringsShapes = new ArrayList<String>();

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
										chosenShapes.add(new Rectangle());
										stringsShapes.add("Rectangle");
									} else if (added.get(i) == "Circle") {
										chosenShapes.add(new Circle());
										stringsShapes.add("Circle");
									}else if (added.get(i) == "Triangle") {
										 stringsShapes.add("Triangle");
									} else if (added.get(i) == "Diamond") {
										stringsShapes.add("Diamond");
									}
									
								}
																
								
							} if (c.wasRemoved()) {
								
								List<? extends String> removed = c.getRemoved();
								for (int i = 0; i<removed.size(); i++) {
									if(removed.get(i) == "Rectangle") {
										chosenShapes.remove(new Rectangle());
										stringsShapes.remove("Rectangle");
									} else if (removed.get(i) == "Circle") {
										chosenShapes.remove(new Circle());
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
	              
			//get selected

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
					
//					engine.generateHand(chosenColors, chosenShapes, numCards);
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
			
			
			for( int cardCount =0; cardCount < numCards; cardCount++){
				cardFront.add(new Rectangle());
				cardFront.get(cardCount).setHeight(150.0);	// needs to add randomization, but nice.
				cardFront.get(cardCount).setWidth(100.0);
				cardFront.get(cardCount).setFill(Color.WHITE);
				cardBacks.add(new ImageView());
				cardBacks.get(cardCount).setImage(new Image(getClass().getResource("cardBackImage.jpg").toExternalForm()));
				
				//add in card objects; square triangles ...
				cards.add(new StackPane());			
				cards.get(cardCount).getChildren().add(cardFront.get(cardCount));
				cards.get(cardCount).getChildren().add(cardBacks.get(cardCount));
			}
			cardArea.getChildren().addAll(cards);
//*******************************************************
			
			RectangleCard actualCard = new RectangleCard();

			ListView<String> CardColorGuess = new ListView<String> (
					
					FXCollections.observableArrayList(stringsColors));

			CardColorGuess.setPrefHeight((24*4)+4);
			CardColorGuess.setPrefWidth(125);

			ListView<String> CardShapeGuess = new ListView<String>(FXCollections.observableArrayList(stringsShapes));

			CardShapeGuess.setPrefHeight((24*4)+4);
			CardShapeGuess.setPrefWidth(125);

			HBox ControlArea = new HBox(10);
			ControlArea.setPadding(new Insets(10,10,10,10));
			ControlArea.getChildren().addAll(CardShapeGuess, CardColorGuess);
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
						for(int tempCount=0; tempCount < numCards; tempCount++){
							cardBacks.get(tempCount).setVisible(true);
						}
						flipCounter=0;
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
	
}