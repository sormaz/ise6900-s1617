package edu.ohiou.ise.ise6900;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.geometry.Pos;

public class LayoutDemoApp extends Application {

	public LayoutDemoApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				primaryStage.setTitle("Demo of layouts");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		Button redBtn = new Button();
//		redBtn.setLayoutX(100);
//		redBtn.setLayoutY(80);
		redBtn.setText("Red");
		redBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.RED);
			}
		});
		Button blueBtn = new Button();
//		blueBtn.setLayoutX(150);
//		blueBtn.setLayoutY(80);
		blueBtn.setText("Blue");
		blueBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.BLUE);
			}
		});
		Button greenBtn = new Button();
//		greenBtn.setLayoutX(200);
//		greenBtn.setLayoutY(80);
		greenBtn.setText("Green");
		greenBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.GREEN);
			}
		});
		Button yellowBtn = new Button();
//		yellowBtn.setLayoutX(250);
//		yellowBtn.setLayoutY(80);
		yellowBtn.setText("Yellow");
		yellowBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.YELLOW);
			}
		});
		Button selectBtn = new Button();
//		selectBtn.setLayoutX(200);
//		selectBtn.setLayoutY(80);
		selectBtn.setText("Select");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// run color chooser
			}
		});
//		HBox layouter = new HBox();
//		VBox layouter = new VBox();
		BorderPane layouter = new BorderPane();
		
		root.getChildren().add(layouter);
		layouter.setTop(redBtn);
		layouter.setBottom(blueBtn);
		layouter.setLeft(greenBtn);
		layouter.setRight(yellowBtn);
		layouter.setCenter(selectBtn);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
