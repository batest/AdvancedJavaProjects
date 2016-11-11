package com.clarkson.batest.ee242;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;


/* TONY'S NOTES
 * Mainly for meeting tomorrow and my thoughts about what needs done...
 					

can we have an observable array list of the colors in the framework plz?
shapes too? then the lists would be wayyyyyy nicer to work with
	--not *NECESSARY* per se, but it'd be useful, and a master list would be easier to update if needed
	-- also, enums?
		--it'd make color/shape determination easier, but idk how to make an enum work quite right in java

also, maybe i should make, like, a cardDisplay class to make an array of and then put in cardArea?
would that work?

	--nope, bad idea, extending node is scary
		--Will that affect Card implementation? i mean, the way it is would be nice, but is
			it possible to use them without extending node?... need to talk about this
				--tried accessing shape within a RectangleCard. no way to set the height to something decent,
					and due to the superclass mandating it be a shape, its not possible to change the rectangle
					height currently :/

or like, make 7 separate cardDisplay -ers and set them invisible if array less than 7?

	--idk if thats possible... maybe make a control area that you change with each flip?
		then you wouldn't be able to pre-guess, but it would make gui implementation far simpler..
		
I'll have a control-area version made for tomorrow

--CARD ARRAY MIGHT WORK
	--just need to figure out a slightly better way to do it

PICTURE FOR CARD BACK?

	--i can make one if needed, or we can use a google image for one

i think the card colors troy made are swing..... idk...
	--also, "CirlcCard" is misspelled lmao thats been bugging me

oh! rectangle.setrotation or something like that would be good to make a diamond card,
HOWEVER, then the resize for card flip will work wacky...

	-- im leaning toward using images for this still
	
if we use an image for the card back then is there a way to display the same image multiple times?
	--not terribly necessary, but it'd be cute to save 6 images worth of memory if possible
	
*****************************************************************************************************************************	
FRAMEWORK IDEA, should it be necessary:
{
	observable array list of colors
	ditto for shapes
	card array counter
	array storing info of each card?
	trial count
	score total
	--score per round maybe? not necessary, but could be nice, idk
	
	random card generator, too
	
	methods i can call from the GUI to:
		--move index for card flipping	-- main flipping function. animation should prolly be handled in the gui but idk.
		--get shapes/colors				<-- needed for begin window to build listview thingies
		--get the current set of cards -- maybe have this callable from the BEGIN BUTTON?
		--set what shapes they want		--again, acts on begin
		--ditto for colors				-- ^^
		--set how many shapes per trial	--^6
}
*****************************************************************************************************************************

*/

public class thePriceIsRightGUI extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	int numCards = 3;		//Placeholder variable for my testing.

	@Override
	public void start(Stage primaryStage) {	// starts the "new game" window. this can be changed if necessary
		try {
			VBox root = new VBox();
			Scene newGame = new Scene(root);
			Insets insets = new Insets(10,10,10,10);

			Label welcome = new Label("Welcome to THE SHAPE IS RIGHT!!\n   ");
			Label ShapeMessage = new Label("Please Select how many shapes you would like to use:     ");

			ChoiceBox<String> selectShapeNum = new ChoiceBox<String>(FXCollections.observableArrayList(
				    "3", "5" , "7")
			);

			HBox numShapes = new HBox();
			numShapes.getChildren().addAll(ShapeMessage, selectShapeNum);

			HBox setup = new HBox();		//contains the game settings
			VBox Shapes = new VBox();
			VBox Colors = new VBox();

			Label whatShape = new Label("What Shapes Would You Like?");
			ListView<String> shapePicker = new ListView<String> (FXCollections.observableArrayList(
					"Rectangle", "Circle", "Triangle", "Diamond"));	//These should be changed once shapes are finalized.
			shapePicker.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			//get selected
			
			
			Label whatColor = new Label("What Colors Would You Like?");
			ListView<String> colorPicker = new ListView<String> (FXCollections.observableArrayList(
					"Blue", "Red", "Green", "Black"));	// these should also likely be changed.
			colorPicker.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
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
					// needs to set framework values to selected values as well.
					// generateHand(arrlist colors, arrlist shapes
					
		//			GameFunctionality.generateHand();

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

			Rectangle[] Cards = new Rectangle[numCards];

			Rectangle Card1 = new Rectangle();
			Card1.setHeight(150.0);
			Card1.setWidth(100.0);

			Rectangle Card2 = new Rectangle();
			Card2.setHeight(150.0);
			Card2.setWidth(100.0);

			Rectangle Card3 = new Rectangle();
			Card3.setHeight(150.0);
			Card3.setWidth(100.0);

			Rectangle Card4 = new Rectangle();
			Card4.setHeight(150.0);
			Card4.setWidth(100.0);

			Rectangle Card5 = new Rectangle();
			Card5.setHeight(150.0);
			Card5.setWidth(100.0);

			Rectangle Card6 = new Rectangle();
			Card6.setHeight(150.0);
			Card6.setWidth(100.0);

			Rectangle Card7 = new Rectangle();
			Card7.setHeight(150.0);
			Card7.setWidth(100.0);

//			Cards[0] = Card1;
//			Cards[1] = Card2;
//			Cards[2] = Card3;

			for(int i = 0; i < numCards; i++) {
				if(i==0) {
					Cards[i]=Card1;
				} else if(i==1) {
					Cards[i]=Card2;
				} else if(i==2) {
					Cards[i]=Card3;
				} else if(i==3) {
					Cards[i]=Card4;
				} else if(i==4) {
					Cards[i]=Card5;
				} else if(i==5) {
					Cards[i]=Card6;
				} else if(i==6) {
					Cards[i]=Card7;
				} else {
					break;
				}
			}

			RectangleCard actualCard = new RectangleCard();

			ListView<String> CardColorGuess = new ListView<String> (FXCollections.observableArrayList(
					"CRIMSON", "JELLYFISH", "GUNMETAL", "pink"));

			CardColorGuess.setPrefHeight((24*4)+4);
			CardColorGuess.setPrefWidth(125);

			ListView<String> CardShapeGuess = new ListView<String> (FXCollections.observableArrayList(
					"SHAPE ONE", "FOURTAGON", "CAR", "M16"));

			CardShapeGuess.setPrefHeight((24*4)+4);
			CardShapeGuess.setPrefWidth(125);

			HBox ControlArea = new HBox(10);
			ControlArea.setPadding(new Insets(10,10,10,10));
			ControlArea.getChildren().addAll(CardShapeGuess, CardColorGuess);
			ControlArea.setAlignment(Pos.CENTER);

//			cardArea.getChildren().addAll(Card1,Card2, Card3);
//			for (int i = 0; i<numCards; i++) {
//				cardArea.getChildren().add(Cards[i]);
//			}
			cardArea.getChildren().addAll(Cards);

			cardArea.setPadding(new Insets(10,10,10,10));
			cardArea.setAlignment(Pos.CENTER);

			Label guess = new Label("Guess the Next Shape and Color!!");

			Button flipNext = new Button("Flip Next Card!");
			flipNext.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
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