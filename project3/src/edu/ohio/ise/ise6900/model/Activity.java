
package edu.ohio.ise.ise6900.model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

public class Activity extends AbstractState {
	
	private Job job;
	protected MfgFeature feature;

	public Activity(Machine machine, Job job, MfgFeature f, double startTime, double endTime) {
		super(machine, startTime, endTime);
		this.job = job;
		feature = f;
		f.activity = this;
	}
	
	public String toString () {
		return "Job " + job.getName() + " " + super.toString();
	}
	
	public  StateOption state() {
		return StateOption.BUSY;
	}

	public void printout () {
		System.out.println(toString());
	}

	public MfgFeature getFeature() {
		// TODO Auto-generated method stub
		return feature;
	}
	
	public Collection<Shape> makeShapes() {
		Collection<Shape> shapes = new ArrayList<Shape>();
		Shape r =makeShape();
		r.setStroke(job.getColor());
		r.setStrokeType(StrokeType.INSIDE);
		r.setStrokeWidth(3);
		shapes.add(r);
		return shapes;
	}
	// change to push


	

}

