package edu.ohio.ise.ise6900.model;

public class MachineState extends AbstractState {
	
	StateOption state;

	public MachineState(Machine machine, String stateOption, double startTime, double endTime) {
		super(machine, startTime, endTime);
		state = StateOption.valueOf(stateOption);
		// TODO Auto-generated constructor stub
	}
	
	public  StateOption state() {
		return state;
	}

	@Override
	public void printout() {
		System.out.println("STate " + toString());
		
	}
	
	public String toString() { 
		return state + " -> " + super.toString();
		
	}

}
