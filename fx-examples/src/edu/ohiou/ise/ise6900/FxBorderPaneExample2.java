package edu.ohiou.ise.ise6900;

/*
 * This example is from 
 * https://examples.javacodegeeks.com/desktop-java/javafx/javafx-borderpane-example/
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxBorderPaneExample2 extends Application
{
	// Create the TextArea for the Output
	private TextArea outputArea = new TextArea();
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) 
	{	
		// Create the Buttons
		Button centerButton = new Button("Center");
		Button rightButton = new Button("Right");
		Button leftButton = new Button("Left");

		// add an EventHandler to the Left Button
		rightButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				writeOutput("You have pressed the Right Button !!!");
			}
		});

		// add an EventHandler to the Left Button
		centerButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				writeOutput("You have pressed the Center Button !!!");
			}
		});
		
		// add an EventHandler to the Left Button
		leftButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				writeOutput("You have pressed the Left Button !!!");
			}
		});
		
		// Set the alignment of the Left Button to Center
		BorderPane.setAlignment(leftButton,Pos.CENTER_LEFT);
		// Set the alignment of the Right Button to Center
		BorderPane.setAlignment(rightButton,Pos.CENTER_RIGHT);
		// Set the alignment of the Center Button to Center
		BorderPane.setAlignment(centerButton,Pos.CENTER);
		
		// Create the upper BorderPane
		BorderPane borderPane = new BorderPane();
		// Set the Buttons to their Location
		borderPane.setLeft(leftButton);
		borderPane.setRight(rightButton);
		borderPane.setCenter(centerButton);
		
		// Create an empty BorderPane
		BorderPane root = new BorderPane();
		// Add the children to the BorderPane
		root.setTop(borderPane);
		root.setBottom(outputArea);
		
		// Set the Size of the VBox
		root.setPrefSize(400, 400);		
		// Set the Style-properties of the BorderPane
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");
		
		// Create the Scene
		Scene scene = new Scene(root);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("A BorderPane Example with only a Top and Bottom Node");
		// Display the Stage
		stage.show();		
	}

	// Method to log the Message to the Output-Area
	private void writeOutput(String msg)
	{
		this.outputArea.appendText("Your Input: " + msg + "\n");
	}	
	
}