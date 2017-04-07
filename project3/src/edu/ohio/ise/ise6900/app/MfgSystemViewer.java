package edu.ohio.ise.ise6900.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.ohio.ise.ise6900.draw.DrawPanel;
import edu.ohio.ise.ise6900.model.MfgSystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MfgSystemViewer extends Application {

	public MfgSystemViewer() {
		// TODO Auto-generated constructor stub
	}

	public static DrawPanel canvas = new DrawPanel();
	MfgSystem ms;

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
		try {
			Parent p = (Parent) FXMLLoader.load(getClass().getResource("mfg-system-viewer.fxml"));
			root.setTop(p);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		canvas.addTarget(ms);
		canvas.makeShapes();
		root.setCenter(canvas);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
