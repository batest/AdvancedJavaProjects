package com.clarkson.batest.ee242;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.geometry.Insets;


public class thePriceIsRightGUI extends Application{
		
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {	// starts the "new game" window. this can be changed if necessary
		try {
			VBox root = new VBox();
			Scene newGame = new Scene(root);	
			Insets insets = new Insets(10,10,10,10);
			
			Label welcome = new Label("Welcome to THE SHAPE IS RIGHT!!");
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
					"SHAPE ONE", "FOURTAGON", "CAR", "M16"));
			
			Label whatColor = new Label("What Colors Would You Like?");
			ListView<String> colorPicker = new ListView<String> (FXCollections.observableArrayList(
					"CRIMSON", "JELLYFISH", "GUNMETAL", "pink"));
			
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
			
			

			root.getChildren().addAll(welcome, numShapes,setup, beginBTN);
			root.setAlignment(Pos.CENTER);
			root.setPadding(insets);
			
			primaryStage.setScene(newGame);
			primaryStage.sizeToScene();
			primaryStage.show();
		}
		catch (Exception e) {
				
		}
	
	
	}
}