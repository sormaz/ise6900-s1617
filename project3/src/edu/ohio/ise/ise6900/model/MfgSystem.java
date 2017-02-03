package edu.ohio.ise.ise6900.model;

import java.util.*;

public class MfgSystem extends MfgObject {
	
	Collection<Job> jobs;
	Collection<Machine> machines;

	public MfgSystem(String n) {
		super(n);
		jobs = new ArrayList<Job>();
		machines = new ArrayList<Machine>();
	}
	
	public void addJob(Job j) throws AlreadyMemberException {
		if (jobs.contains(j)) {
			throw new AlreadyMemberException("Job " + j + " is alreaady in the mfg system " + this);
		}
		jobs.add(j);
	}

	public void printJobes() {
		for (Job j : jobs) {
			System.out.println(j.toString());
		}
		
	}

	public void addMachine(Machine machine) throws AlreadyMemberException {
		if (machines.contains(machine)) {
			throw new AlreadyMemberException("MAchine " + machine + " is alreaady in the mfg system " + this);
		}
		machines.add(machine);
		
	}

}
