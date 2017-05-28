package edu.ohio.ise.ise6900.exam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonApplication extends Application {
	private Button b1 = new Button ("b1"); 
	private Button b2 = new Button ("b2");
	private Button b3 = new Button ("b3"); 
	private Button b4 = new Button ("b4");
	private String myTitle = "Exam Button Application";

	public void start(Stage primaryStage) {
			VBox root = new VBox();
			Scene scene = new Scene(root,400,400);
			root.getChildren().add(b1);
			root.getChildren().add(b4);
			root.getChildren().add(b2);
			primaryStage.setTitle(myTitle);
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
