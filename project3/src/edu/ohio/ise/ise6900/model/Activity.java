
package edu.ohio.ise.ise6900.model;

public class Activity extends AbstractState {
	
	Job job;

	public Activity(Machine machine, Job job, double startTime, double endTime) {
		super(machine, startTime, endTime);
		this.job = job;
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
}

