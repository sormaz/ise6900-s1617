package edu.ohiou.ise.ise6900;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.*;

public class StylesheetDemoApp extends Application {

	public StylesheetDemoApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		setUserAgentStylesheet(STYLESHEET_MODENA);
		primaryStage.setTitle("Hello World");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		Button caspianBtn = new Button();
		caspianBtn.setLayoutX(100);
		caspianBtn.setLayoutY(80);
		caspianBtn.setText("CASPIAN");
		caspianBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				setUserAgentStylesheet(STYLESHEET_CASPIAN);
			}
		});
		Button modenaBtn = new Button();
		modenaBtn.setLayoutX(200);
		modenaBtn.setLayoutY(80);
		modenaBtn.setText("MODENA");
		modenaBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				setUserAgentStylesheet(STYLESHEET_MODENA);
			}
		});

		root.getChildren().add(caspianBtn);
		root.getChildren().add(modenaBtn);

		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
