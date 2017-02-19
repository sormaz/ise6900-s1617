package edu.ohio.ise.ise6900.model;

public class MachineState extends AbstractState {
	
	StateOption state;

	public MachineState(Machine machine, double startTime, double endTime, String stateOption) {
		super(machine, startTime, endTime);
		// TODO Auto-generated constructor stub
	}
	
	public  StateOption state() {
		return state;
	}
	
	public void printout () {
		System.out.println(toString());
	}

}
