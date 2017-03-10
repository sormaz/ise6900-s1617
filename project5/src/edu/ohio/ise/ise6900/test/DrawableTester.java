package edu.ohio.ise.ise6900.test;

import java.util.ArrayList;
import java.util.Collection;



import edu.ohio.ise.ise6900.draw.DrawApplication;
import edu.ohio.ise.ise6900.draw.Drawable;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class DrawableTester  implements Drawable {

	public DrawableTester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		DrawableTester dt = new DrawableTester();
		dt.display(args);

	}

	@Override
	public Collection<Shape> makeShapes() {
		
		Collection<Shape> shapes = new ArrayList<Shape>();
		Line line = new Line (30,50,70,50);
				line.setStrokeWidth(5.0);
				line.setStroke(Color.RED);
		shapes.add(line);
		return shapes;
	}
	
	public void display (String [] args) {
		DrawApplication da = new DrawApplication();
		da.getCanvas().addTarget(this);
		da.main(args);
	}



}
