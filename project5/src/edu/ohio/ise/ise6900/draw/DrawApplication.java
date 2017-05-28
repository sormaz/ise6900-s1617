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
	Stage stage;
	
	public DrawApplication() {
		
	}

	public void start(Stage primaryStage) {
		stage = primaryStage;
		primaryStage.setTitle("MfgSystem");
		Group root = new Group();
		Scene scene = new Scene(canvas, 300, 250);
		canvas.makeShapes();
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

	/**
	 * attempt to avoid crash when the second display is called
	 * it does not work
	 */
//	public void show() {
//		Thread th = new Thread() {
//			public void run ()  {
//				stage = new Stage();
//				DrawApplication.this.start(stage);	
//			}
//		};
//		th.start();
//	}

}
