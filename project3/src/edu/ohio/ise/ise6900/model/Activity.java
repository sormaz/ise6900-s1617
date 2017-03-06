
package edu.ohio.ise.ise6900.model;

public class Activity extends AbstractState {
	
	private Job job;
	protected MfgFeature feature;

	public Activity(Machine machine, Job job, MfgFeature f, double startTime, double endTime) {
		super(machine, startTime, endTime);
		this.job = job;
		feature = f;
		f.activity = this;
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

	public MfgFeature getFeature() {
		// TODO Auto-generated method stub
		return feature;
	}
	

}

