package edu.ohiou.ise.ise6900;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;;

public class ButtonDemoApp extends Application {

	public ButtonDemoApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				primaryStage.setTitle("Hello World");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		
		class ColorHandler<Event> implements EventHandler<ActionEvent> {
			
			Color background;
			 public ColorHandler (Color c) {
				background = c;
			}
			public void handle(ActionEvent event) {
				scene.setFill(background);
			}
		}
		
		Button redBtn = new Button();
		redBtn.setLayoutX(50);
		redBtn.setLayoutY(80);
		redBtn.setText("Red");

		redBtn.setOnAction(new ColorHandler<ActionEvent>(Color.RED));
		Button yellowBtn = new Button();
		yellowBtn.setLayoutX(150);
		yellowBtn.setLayoutY(80);
		yellowBtn.setText("Yellow");
		yellowBtn.setOnAction(new ColorHandler<ActionEvent>(Color.YELLOW));
		Button greenBtn = new Button();
		greenBtn.setLayoutX(250);
		greenBtn.setLayoutY(80);
		greenBtn.setText("Green");
		greenBtn.setOnAction(new ColorHandler<ActionEvent>(Color.GREEN));
		root.getChildren().add(redBtn);
		root.getChildren().add(yellowBtn);
		root.getChildren().add(greenBtn);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
	

}


