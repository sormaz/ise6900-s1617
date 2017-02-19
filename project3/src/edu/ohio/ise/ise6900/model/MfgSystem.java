package edu.ohio.ise.ise6900.model;

import java.util.*;

public class MfgSystem extends MfgObject {
	
	Map<String,Job> jobs;
	Map<String,Machine> machines;

	public MfgSystem(String n) {
		super(n);
		jobs = new HashMap<String,Job>();
		machines = new HashMap<String,Machine>();
	}
	
	public void addJob(Job j) throws AlreadyMemberException {
		try {
			findJob(j.getName());
			throw new AlreadyMemberException("Job " + j.getName() + " is alreaady in the mfg system " + this.getName());
		} catch (UnknownObjectException e) {
			jobs.put(j.getName(), j);	
		}
	}

	public Job findJob(String jobName) throws UnknownObjectException{
		Job job =  jobs.get(jobName);
		if (job == null)
			throw new UnknownObjectException("job with name " + jobName + " does not exist.");
		return job;
	}

	public void printJobs() {
		for (Job j : jobs.values()) {
			System.out.println(j.toString());
		}
		
	}

	public void addMachine(Machine m) throws AlreadyMemberException {
		try {
			findMachine(m.getName());
			throw new AlreadyMemberException("Machine " + m.getName() + " is alreaady in the mfg system " + this);
		} catch (UnknownObjectException e) {
			machines.put(m.getName(), m);		
		}	
	}

	public void printMachines() {
		for (Machine m : machines.values()) {
			System.out.println(m.toString());
		}
		
	}
	
	public Machine findMachine(String machineName) throws UnknownObjectException{
		Machine machine =  machines.get(machineName);
		if (machine == null)
			throw new UnknownObjectException("Machine with name " + machineName + " does not exist.");
		return machine;
	}
	
	public String toString () {
		return "MfgSystem " + getName() + "contains " + jobs.size() + " Jobs and "  + machines.size() + " machines";
		
	}
	
	public void printout () {
		System.out.println(toString());
	}

}
