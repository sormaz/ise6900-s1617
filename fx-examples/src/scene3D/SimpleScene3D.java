package scene3D;

/* From SimpleScene3D.java */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class SimpleScene3D extends Application {
	private PerspectiveCamera camera;
	private final double cameraModifier = 50.0;
	private final double cameraQuantity = 10.0;
	private final double sceneWidth = 600;
	private final double sceneHeight = 600;
	private double mouseXold = 0;
	private double mouseYold = 0;
	private final double cameraYlimit = 15;
	private final double rotateModifier = 25;
	
	@Override
	public void start(Stage primaryStage) {
		//Step 1a: Build your Scene and Camera
		Group sceneRoot = new Group();
		Scene scene = new Scene(sceneRoot, sceneWidth, sceneHeight);
		scene.setFill(Color.BLACK);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setNearClip(0.1);
		camera.setFarClip(10000.0);
		camera.setTranslateZ(-1000);
		scene.setCamera(camera);
		primaryStage.setTitle("SimpleScene3D");
		primaryStage.setScene(scene);
		
		//Step 1b: Add a primitive
		final Cylinder cylinder = new Cylinder(50, 100);
		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);
		cylinder.setMaterial(blueMaterial);
		sceneRoot.getChildren().add(cylinder);
		primaryStage.show(); 
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}


