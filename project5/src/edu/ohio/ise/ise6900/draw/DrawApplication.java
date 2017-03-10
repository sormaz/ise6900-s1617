package edu.ohio.ise.ise6900.draw;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DrawApplication extends Application {
	static DrawPanel canvas = new DrawPanel();;

	public DrawApplication() {
		
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hello World");
		Group root = new Group();
		Scene scene = new Scene(canvas, 300, 250);

//		root.getChildren().add(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch (args);

	}

	public DrawPanel getCanvas() {
		// TODO Auto-generated method stub
		return canvas;
	}

}
