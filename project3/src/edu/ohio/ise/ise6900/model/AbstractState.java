package edu.ohio.ise.ise6900.model;

public abstract class AbstractState implements Comparable<AbstractState> {
	

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

}

enum StateOption { BUSY, IDLE, DOWN, BLOCKED}
