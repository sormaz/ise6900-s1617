package edu.ohiou.ise.ise6900;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

public class HeroPickerApp extends Application {

	public HeroPickerApp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hero Picker: Chapter 4 Creating and Working with ObservableLists");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 250, Color.WHITE);
		// create a grid pane
		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(10);
		gridpane.setVgap(10);
		ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		gridpane.getColumnConstraints().addAll(column1, column2, column3);
		// Candidates label
		Label candidatesLbl = new Label("Candidates");
		GridPane.setHalignment(candidatesLbl, HPos.CENTER);
		gridpane.add(candidatesLbl, 0, 0);	
		// Heroes label
		Label heroesLbl = new Label("Heroes");
		gridpane.add(heroesLbl, 2, 0);
		GridPane.setHalignment(heroesLbl, HPos.CENTER);
		// Candidates
		final ObservableList<String> candidates = FXCollections.observableArrayList("Superman",
				"Spiderman",
				"Wolverine",
				"Police",
				"Fire Rescue",
				"Soldiers",
				"Dad & Mom",
				"Doctor",
				"Politician",
				"Pastor",
				"Teacher");
		final ListView<String> candidatesListView = new ListView<>(candidates);
		gridpane.add(candidatesListView, 0, 1);
		// heroes
		final ObservableList<String> heroes = FXCollections.observableArrayList();
		final ListView<String> heroListView = new ListView<>(heroes);
		gridpane.add(heroListView, 2, 1);
		// select heroes
		Button sendRightButton = new Button(" > ");
		sendRightButton.setOnAction((ActionEvent event) -> {
			String potential = candidatesListView.getSelectionModel().getSelectedItem();
			if (potential != null) {
				candidatesListView.getSelectionModel().clearSelection();
				candidates.remove(potential);
				heroes.add(potential);
			}
		});
		// deselect heroes
		Button sendLeftButton = new Button(" < ");
		sendLeftButton.setOnAction((ActionEvent event) -> {
			String notHero = heroListView.getSelectionModel().getSelectedItem();
			if (notHero != null) {
				heroListView.getSelectionModel().clearSelection();
				heroes.remove(notHero);
				candidates.add(notHero);
			}
		});
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(sendRightButton,sendLeftButton);
		gridpane.add(vbox, 1, 1);
		root.setCenter(gridpane);
		GridPane.setVgrow(root, Priority.ALWAYS);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
