package edu.ohio.ise.ise6900.model;

public abstract class AbstractState {
	

	private Machine machine;
	private double startTime;
	private double endTime;
	
	public AbstractState(Machine machine, double startTime, double endTime) {
		super();
		this.machine = machine;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public double duration () {
		return endTime - startTime;
	}
	
	public abstract StateOption state();

public String toString() {
	return "Machine" + machine.getName() + " starts at " + startTime + " duration "+ duration();
	
}

}

enum StateOption { BUSY, IDLE, DOWN, BLOCKED}
