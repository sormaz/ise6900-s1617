package edu.ohio.ise.ise6900.model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public abstract class AbstractState extends MfgObject implements Comparable<AbstractState> {
	
	static double OFFSET = Double.parseDouble(getProperty("OFFSET", "20"));
	

	private Machine machine;
	private double startTime;
	private double endTime;
	
	public AbstractState(Machine machine, double startTime, double endTime) {
		super("act");
		// verify start end end times
		if (startTime >= endTime) {
			throw new IllegalArgumentException(this.getClass().getName() + " with start time " + startTime + 
							" larger then end time " + endTime + " is impossible");
		}
		this.machine = machine;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public double duration () {
		return endTime - startTime;
	}
	public Machine getMchine () {
		return machine;
	}
	
	@Override
	public int compareTo(AbstractState as) {
		if (this.equals(as)) return 0;
		int res = Double.compare(startTime, as.startTime);
		if (res != 0) return res;
		res = Double.compare(endTime, as.endTime);
		if (res != 0) return res;
		return machine.getName().compareTo(as.machine.getName());
	}
	
	public boolean equals (Object o) {
		if (o == null) return false;
		if (this == o) return true;
		if (this.getClass().equals(o.getClass())) {
			AbstractState as = (AbstractState) o;
			return machine.equals(as.machine) &&
					endTime == as.endTime &&
					startTime == as.startTime;
		}
		return false;
	}
	
	public abstract StateOption state();

public String toString() {
	return "Machine " + machine.getName() + " starts at " + startTime + " duration "+ duration();
	
}

public Collection<Shape> makeShapes() {
	
	Collection<Shape> shapes = new ArrayList<Shape>();
	Line line = new Line (OFFSET + startTime,machine.getY(), OFFSET + endTime,machine.getY());
			line.setStrokeWidth(5.0);
			line.setStroke(Color.RED);
	shapes.add(line);
	return shapes;
}



}

enum StateOption { BUSY, IDLE, DOWN, BLOCKED}
