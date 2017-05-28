package edu.ohio.ise.ise6900.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.ohio.ise.ise6900.draw.DrawPanel;
import edu.ohio.ise.ise6900.gui.MfgSystemViewer;
import edu.ohio.ise.ise6900.model.MfgSystem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MfgSystemFXApp2 extends Application {

	public MfgSystemFXApp2() {
		// TODO Auto-generated constructor stub
	}

	public static DrawPanel canvas = new DrawPanel();
	public static MfgSystem ms;
	@FXML
	private ListView machineList;

	@Override
	public void start(Stage primaryStage) {
		Parameters parameters = this.getParameters();
		System.out.println("params: " + parameters);
		
//		try {
//			ms = new MfgSystem(new File (parameters.getUnnamed().get(0)));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("File not found, making emptty MfgSystem");
//			ms = new MfgSystem ("Empty");
//		}
		primaryStage.setTitle("Manufacturing System Viewer - FSS 2.1");
		BorderPane root = new BorderPane();
//		root.setDividerPosition(0, 150);
//		root.setOrientation(Orientation.HORIZONTAL);

		Scene scene = new Scene(root, 1000, 600);
		
		Parent p = null;
		try {
			 p = (Parent) FXMLLoader.load(getClass().getResource("mfg-system-fxapp.fxml"));
			
//			root.getItems().add(p);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			root.setTop(p);

//		canvas.addTarget(ms);
		canvas.makeShapes();
//		root.getItems().add(canvas);
		root.setLeft(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
