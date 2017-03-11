package edu.ohio.ise.ise6900.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.sun.javafx.collections.ObservableListWrapper;

import edu.ohio.ise.ise6900.draw.DrawPanel;
import edu.ohio.ise.ise6900.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MfgSystemUIApp extends Application {
		public static DrawPanel canvas = new DrawPanel();
	MfgSystem ms;

		@Override
		public void start(Stage primaryStage) {
			try {
			 ms = new MfgSystem(new File ("sample.mfg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found, making emptty MfgSystem");
				ms = new MfgSystem ("Empty");
			}
			primaryStage.setTitle("Manufacturing System Viewer - FSS 2.0");
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1000, 600);
			ListView<Machine> machineListView = new ListView<Machine> (new ObservableListWrapper<Machine>((List) ms.getMachines()));
			TreeView<MfgObject> jobTreeView = new TreeView(makeMfgSystemTree(ms));
			
			machineListView.setTooltip(new Tooltip("View machines in the system"));
			jobTreeView.setTooltip(new Tooltip("View jobs and features in the system"));
			Line line = new Line (30,250,70,250);
			line.setStrokeWidth(25.0);
			line.setStroke(Color.RED);
			canvas.getChildren().add(line);
			SplitPane centerPane = new SplitPane(new Label("top"),  new Label("middle"), canvas);
			centerPane.setOrientation(Orientation.VERTICAL);
			MenuBar menuBar = new MenuBar(new Menu("File"), new Menu("Edit"));
			ToolBar toolBar = new ToolBar(new Button ("Open"));
			
			root.setTop(menuBar);
			root.setLeft(machineListView);
			root.setRight(jobTreeView);
			root.setCenter(centerPane);
			root.setBottom(toolBar);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		public TreeItem<MfgObject> makeMfgSystemTree(MfgSystem ms) {
			TreeItem<MfgObject> item = new TreeItem<MfgObject>(ms);
			for (Job j : ms.getJobs()) {
				item.getChildren().add(makeJobTree(j));
			}
			return item;
			
		}
		
		public TreeItem<MfgObject> makeJobTree (Job j) {
			TreeItem<MfgObject> item = new TreeItem<MfgObject>(j);
			for (MfgFeature f : j.getFeatures()) {
				item.getChildren().add(new TreeItem<MfgObject>(f));
			}
			return item;	
		}

		public static void main(String[] args) {
			launch(args);
		}
	}


