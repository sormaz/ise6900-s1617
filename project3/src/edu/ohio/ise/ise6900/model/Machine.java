package edu.ohio.ise.ise6900.model;

import java.util.Collection;

public class Machine extends MfgObject {
	
	Collection<AbstractState> states;

	public Machine(String n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	public void addState(AbstractState a) {
		// TODO Auto-generated method stub
		// verify existence of overlapping state
		throw new UnsupportedOperationException("Method addState() not implemented yet");
	}
	
	@Override
	public void printout() {
		throw new UnsupportedOperationException("Method printout() not implemented yet");
		
	}

	public void listStates() {
		System.out.println("States for machine " + getName());
		for (AbstractState s : states) {
			System.out.println("\t" + s.toString());
		}
		
	}

}
