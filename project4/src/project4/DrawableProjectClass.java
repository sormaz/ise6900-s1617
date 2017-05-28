package project4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

import javax.swing.WindowConstants;

import edu.ohio.ent.cs4500.draw.DrawPanel;
import edu.ohio.ent.cs4500.draw.Drawable;

public class DrawableProjectClass implements Drawable {
	DrawPanel dp = new DrawPanel(this);

	public DrawableProjectClass() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void display() {
		display("Title", new Dimension (400,400), WindowConstants.EXIT_ON_CLOSE);

	}

	@Override
	public void display(String title, Dimension size, int closeOperation) {
		dp.display(title, size, closeOperation);

	}

	@Override
	public void makeDrawSet(DrawPanel canvas) {
		canvas.addShape(Color.RED, new Ellipse2D.Double(100, 100, 500, 500));

	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub

	}
	
	public static void main (String [] args) {
		DrawableProjectClass dpc = new DrawableProjectClass();
		dpc.display();
	}

}
