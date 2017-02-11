package edu.ohio.ise.ise6900.model;

public class Activity extends AbstractState {
	
	Job job;

	public Activity(Machine machine, Job job, double startTime, double endTime) {
		super(machine, startTime, endTime);
		
		this.job = job;
		// TODO Auto-generated constructor stub
	}
	
	public String toString () {
		return "Job " + job.getName() + " " + super.toString();
	}
	public  StateOption state() {
		return StateOption.BUSY;
	}

	@Override
	public void printout() {
		// TODO Auto-generated method stub
		
	}



}
