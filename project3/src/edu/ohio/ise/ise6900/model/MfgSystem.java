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
		jobs.add(j);
	}

}
