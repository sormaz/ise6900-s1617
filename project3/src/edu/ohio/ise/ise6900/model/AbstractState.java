package edu.ohio.ise.ise6900.model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public abstract class AbstractState extends MfgObject implements Comparable<AbstractState> {

	//	static double OFFSET = Double.parseDouble(getProperty("OFFSET", "20"));


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
	public Machine getMachine () {
		return machine;
	}
	
	public double getStartTime() {
		return startTime;
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
		Shape r =makeShape();
		shapes.add(r);
		shapes.addAll(makeXAxis());
		return shapes;
	}
	
	private Collection<Shape> makeXAxis() {
		Collection<Shape> shapes = new ArrayList<Shape>();
		Rectangle r = new Rectangle(0, 0, 500 * SCALE, 600);
		r.setFill(null);
		r.setStroke(Color.BLACK);
		shapes.add(r);
		return shapes;
	}

	protected Shape makeShape () {
		Line line = new Line (OFFSET + SCALE * startTime,machine.getY(), OFFSET + SCALE * endTime,machine.getY());
		line.setStrokeWidth(5.0);
		line.setStroke(state().getColor());
		line.setStrokeLineCap(StrokeLineCap.BUTT);
		Rectangle r = new Rectangle(OFFSET + SCALE * startTime,machine.getY(), SCALE * duration(),HEIGHT);
		r.setStroke(Color.WHITESMOKE);
		r.setFill(state().getColor());
		Tooltip t = new Tooltip(toString());
		Tooltip.install(r, t);
		return r;
	}
	public static Collection<StateOption> getStateOptions() {
		Collection<StateOption> coll = new ArrayList<StateOption> ();
		coll.add(StateOption.BLOCKED);
		coll.add(StateOption.BUSY);
		coll.add(StateOption.IDLE);
		coll.add(StateOption.DOWN);
		return coll;
		
	}
}


