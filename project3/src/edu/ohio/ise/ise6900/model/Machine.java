package edu.ohio.ise.ise6900.model;

import java.util.*;

public class Machine extends MfgObject {
	
	Collection<AbstractState> states;

	public Machine(String n) {
		super(n);
		states = new TreeSet<AbstractState> ();
	}

	public void addState(AbstractState a) {
		states.add(a);
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
