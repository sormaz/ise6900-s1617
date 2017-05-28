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
	
	Pane layouter;
	Button redBtn;
	Button blueBtn;
	Button greenBtn;
	Button yellowBtn;
	Button selectBtn;
	Stage s;

	public LayoutDemoApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				primaryStage.setTitle("Demo of layouts");
				s = primaryStage;
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		 redBtn = new Button();
//		redBtn.setLayoutX(100);
//		redBtn.setLayoutY(80);
		redBtn.setText("Red");
		
		redBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.RED);
			}
		});
		 blueBtn = new Button();
//		blueBtn.setLayoutX(150);
//		blueBtn.setLayoutY(80);
		blueBtn.setText("Blue");
		blueBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.BLUE);
			}
		});
		 greenBtn = new Button();
//		greenBtn.setLayoutX(200);
//		greenBtn.setLayoutY(80);
		greenBtn.setText("Green");
		greenBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.GREEN);
			}
		});
		 yellowBtn = new Button();
//		yellowBtn.setLayoutX(250);
//		yellowBtn.setLayoutY(80);
		yellowBtn.setText("Yellow");
		yellowBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.YELLOW);
			}
		});
		 selectBtn = new Button();
//		selectBtn.setLayoutX(200);
//		selectBtn.setLayoutY(80);
		selectBtn.setText("Select");
		selectBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// run color chooser
			}
		});
	
		makeGridPane();
//		makeHBox();
//		makeVBox();
//		makeBorderPane();
//		makeFlowPane();
		
		root.getChildren().add(layouter);

		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	private void makeBorderPane() {
		BorderPane bp;
		s.setTitle("BorderPane");
		 layouter = bp = new BorderPane();
		 bp.setTop(redBtn);
		 bp.setBottom(blueBtn);
		 bp.setLeft(greenBtn);
		 bp.setRight(yellowBtn);
		 bp.setCenter(selectBtn);
	}
	
	private void makeHBox() {
		HBox hb;
		s.setTitle("HBox");
		layouter = hb =  new HBox();
		hb.getChildren().add(redBtn);
		hb.getChildren().add(blueBtn);
		hb.getChildren().add(greenBtn);
		hb.getChildren().add(yellowBtn);
		hb.getChildren().add(selectBtn);
	}
	
	private void makeVBox() {
		VBox vb;
		s.setTitle("VBox");
		layouter = vb =  new VBox();
		vb.getChildren().add(redBtn);
		vb.getChildren().add(blueBtn);
		vb.getChildren().add(greenBtn);
		vb.getChildren().add(yellowBtn);
		vb.getChildren().add(selectBtn);
	}
	
	private void makeGridPane() {
		GridPane gp;
		s.setTitle("GridPane");
		layouter = gp =  new GridPane();
		gp.add(redBtn,0,0);
		gp.add(blueBtn,0,1);
		gp.add(greenBtn,1,0);
		gp.add(yellowBtn,1,1);
		gp.add(selectBtn,2,1);
	}
	
	private void makeFlowPane() {
		FlowPane fp;
		s.setTitle("FlowPane");
		layouter = fp =  new FlowPane();
		fp.setPrefWrapLength(100);
		fp.getChildren().add(redBtn);
		fp.getChildren().add(blueBtn);
		fp.getChildren().add(greenBtn);
		fp.getChildren().add(yellowBtn);
		fp.getChildren().add(selectBtn);
	}
	
	

	public static void main(String[] args) {
		launch(args);

	}

}
