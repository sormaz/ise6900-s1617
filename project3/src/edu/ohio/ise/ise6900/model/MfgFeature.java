package edu.ohio.ise.ise6900.model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class MfgFeature extends MfgObject {
	
	Job job;
	Activity activity;

	public MfgFeature(String n) {
		super(n);

	}
	
	@Override
	public void printout() {
		System.out.println("MfgFeature " + getName() +
				"\n\tJob" + job.getName() +
				"\n\tActivity " + activity.toString());
		
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

}
