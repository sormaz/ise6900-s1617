package edu.ohiou.ise.ise6900;

import java.util.ArrayList;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFXControlsDemo extends Application {

	public JavaFXControlsDemo() {
		// TODO Auto-generated constructor stub
	}


	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Demo of some JavaFX Controls");
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		HBox hb = new HBox();
		Label label = new Label("I am a label");
		TextField textField = new TextField("enter something");
//		textField.
		
		Button redBtn = new Button();
		redBtn.setLayoutX(50);
		redBtn.setLayoutY(80);
		redBtn.setText("Red");
		redBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				scene.setFill(Color.RED);
			}
		});
		
		CheckBox checkBox = new CheckBox();
		ColorPicker colorPicker = new ColorPicker();
		ToggleButton toggleButton = new ToggleButton("R/B");
		ArrayList<String> aList = new ArrayList<String>();
		aList.add("dns");
		aList.add("abc");
		ObservableList<String> list = new ObservableListWrapper<String>(aList);

		ComboBox<String> comboBox = new ComboBox<String> (list);
		TextArea textArea = new TextArea("Add text");
		
		hb.getChildren().add(label);
		hb.getChildren().add(textField);
		hb.getChildren().add(redBtn);
		hb.getChildren().add(checkBox);
		hb.getChildren().add(colorPicker);
		hb.getChildren().add(toggleButton);
		hb.getChildren().add(comboBox);
		hb.getChildren().add(textArea);
		
		root.getChildren().add(hb);

		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {

		launch (args);
	}

}
