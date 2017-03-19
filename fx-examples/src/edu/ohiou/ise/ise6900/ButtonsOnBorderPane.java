package edu.ohiou.ise.ise6900;
/*
 * this example is from 
 * http://www.java2s.com/Tutorials/Java/JavaFX/0330__JavaFX_BorderPane.htm
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/*from w  ww  .ja va2 s  . com*/
public class ButtonsOnBorderPane extends Application {
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("BorderPane Test");
    BorderPane bp = new BorderPane();
    bp.setPadding(new Insets(10, 20, 10, 20));

    Button btnTop = new Button("Red");
    bp.setTop(btnTop);

    Button btnLeft = new Button("Green");
    bp.setLeft(btnLeft);

    Button btnCenter = new Button("Select");
    bp.setCenter(btnCenter);

    Button btnRight = new Button("Yellow");
    bp.setRight(btnRight);

    Button btnBottom = new Button("Blue");
    bp.setBottom(btnBottom);

    Scene scene = new Scene(bp, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
