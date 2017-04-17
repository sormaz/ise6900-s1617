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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MfgSystemFXApp2 extends Application {

	public MfgSystemFXApp2() {
		// TODO Auto-generated constructor stub
	}

	public static DrawPanel canvas = new DrawPanel();
	MfgSystem ms;
	@FXML
	private ListView machineList;

	@Override
	public void start(Stage primaryStage) {
		Parameters parameters = this.getParameters();
		System.out.println("params: " + parameters);
		
		try {
			ms = new MfgSystem(new File (parameters.getUnnamed().get(0)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found, making emptty MfgSystem");
			ms = new MfgSystem ("Empty");
		}
		primaryStage.setTitle("Manufacturing System Viewer - FSS 2.1");
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1000, 600);
		
		Parent p = null;
		try {
			 p = (Parent) FXMLLoader.load(getClass().getResource("mfg-system-fxapp.fxml"));
			
			root.setTop(p);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			root.setTop(p);

		canvas.addTarget(ms);
		canvas.makeShapes();
		root.setCenter(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	private void handleOpenFileAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open STL File");

		File existDirectory = 
				new File(".");

		if(existDirectory.exists()) {
			fileChooser.setInitialDirectory(existDirectory);
		} else {
			fileChooser.setInitialDirectory(new File("."));
		}

		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("STL File", "*.stl"),
				new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			System.out.println("--->selected file is " +  selectedFile);
		}
	}
	
	

	public static void main(String[] args) {
		launch(args);

	}

}
