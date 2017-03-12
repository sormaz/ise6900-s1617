package edu.ohio.ise.ise6900.draw;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

public class DrawPanel extends Group {
	Collection<Drawable> targets = new ArrayList();

	public DrawPanel () {
		
	}
	
	public DrawPanel(Drawable t) {
		targets.add(t);
	}
	public DrawPanel (Collection<Drawable> targets) {
		this.targets.addAll(targets);
	}

	public DrawPanel(double width, double height) {
//		super(width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void addTarget (Drawable t) {
		targets.add(t);		
//		this.getChildren().addAll(t.makeShapes());
	}
	
	public void clear ( ) {
		targets.clear();
		this.getChildren().clear();
	}
	
	public void makeShapes () {
		for (Drawable d : targets) {
			this.getChildren().addAll(d.makeShapes());
		}
	}
	

}
