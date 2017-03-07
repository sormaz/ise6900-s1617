package edu.ohiou.ise.ise6900;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.*;

public class ButtonDemoApp extends Application {

	public ButtonDemoApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				primaryStage.setTitle("Hello World");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		Button redBtn = new Button();
		redBtn.setLayoutX(100);
		redBtn.setLayoutY(80);
		redBtn.setText("Red");
		redBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.RED);
			}
		});
		Button blueBtn = new Button();
		blueBtn.setLayoutX(150);
		blueBtn.setLayoutY(80);
		blueBtn.setText("Blue");
		blueBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.BLUE);
			}
		});
		Button greenBtn = new Button();
		greenBtn.setLayoutX(200);
		greenBtn.setLayoutY(80);
		greenBtn.setText("Green");
		greenBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.GREEN);
			}
		});
		root.getChildren().add(redBtn);
		root.getChildren().add(blueBtn);
		root.getChildren().add(greenBtn);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
