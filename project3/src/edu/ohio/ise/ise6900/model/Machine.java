package edu.ohio.ise.ise6900.model;

import java.util.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Machine extends MfgObject {
	
	Collection<AbstractState> states;

	public Machine(String n) {
		super(n);
		states = new TreeSet<AbstractState> ();
	}

	public void addState(AbstractState a) {
		states.add(a);
	}
	
	@Override
	public void printout() {
		System.out.println("MAchine " + getName() + "\n\tNumber of States " + states.size());	
	}

	public void listStates() {
		System.out.println("States for machine " + getName());
		for (AbstractState s : states) {
			System.out.println("\t" + s.toString());
		}
	}

	public void deleteState(AbstractState a) {
		// TODO Auto-generated method stub
	}
	
	public Collection<Shape> makeShapes() {
		
		Collection<Shape> shapes = new ArrayList<Shape>();
		for (AbstractState a : states) {
		shapes.addAll(a.makeShapes());
		}
		return shapes;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return Double.parseDouble(getProperty(getClass().getName() + "." + getName(), "100"));
	}

}
