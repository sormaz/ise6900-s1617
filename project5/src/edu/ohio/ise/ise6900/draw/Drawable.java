package edu.ohio.ise.ise6900.draw;

import java.util.Collection;

import javafx.scene.shape.Shape;

public interface Drawable {
	
	public Collection<Shape> makeShapes();
	public void display(String [] args);

}
