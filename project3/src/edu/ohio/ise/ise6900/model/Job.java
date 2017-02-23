package edu.ohio.ise.ise6900.model;

import java.util.*;

public class Job extends MfgObject {
	
	public static Map<String, Job> jobMap = new HashMap<String, Job> ();
	
	private int batchSize;
	Map<String, MfgFeature> featureMap;
	Collection<Activity> activities;

	public Job(String n, int batchSize) {
		super(n);
		this.batchSize = batchSize;
		featureMap = new HashMap<String, MfgFeature>();
		jobMap.put(n,  this);
		activities = new TreeSet<Activity>();
	}
	
	public String toString () {
		return "Job " + getName() + ", batch size " + batchSize;
	}
	
	public void addFeature(MfgFeature f) throws AlreadyMemberException {
		if (featureMap.keySet().contains(f.getName())) {
			throw new AlreadyMemberException("Job " + this.getName() + " already contains feature " + f.getName());
		}		
		featureMap.put(f.getName(), f);
		f.job = this;
	}
	public void addFeature(String featureName) throws AlreadyMemberException {
		if (featureMap.keySet().contains(featureName)) {
			throw new AlreadyMemberException("Job " + this.getName() + " already contains feature " + featureName);
		}		
		featureMap.put(featureName, new MfgFeature(featureName));
	}

	public boolean hasFeature(String fName) {
		throw new UnsupportedOperationException("Method hasFeature() not implemented yet");
	}

	public static Job findJob(String jobName) throws UnknownObjectException {
		// TODO Auto-generated method stub
		Job job =  jobMap.get(jobName);
		if (job == null)
			throw new UnknownObjectException("job with name " + jobName + " does not exist.");
		return job;
}

	public MfgFeature findFeature(String featureName) throws UnknownObjectException {
		MfgFeature f =  featureMap.get(featureName);
		if (f == null)
			throw new UnknownObjectException("Feature with name " + featureName + " does not exist.");
		return f;
	}

	public void addActivity(Activity activity) throws AlreadyMemberException {
		boolean isAdded = activities.add(activity);
		if (!isAdded) throw new AlreadyMemberException(activity.toString() + " already exists");
		
	}
	
	public void listActivities () {
		System.out.println("Activities for job " + getName());
		for (Activity a : activities) {
			System.out.println("\t" + a.toString());
		}
	}

	@Override
	public void printout() {
		System.out.println(toString() 
				+ "\n\tNumber of features " + featureMap.size() +
				"\n\tNumber of activities " + activities.size());
		
		
	}

	public void listFeatures() {
		System.out.println("Features for job " + getName());
		for (MfgFeature f : featureMap.values()) {
			System.out.println("\t" + f.toString());
		}
		
	}

	public Activity findActivity(Machine m, MfgFeature f) throws UnknownObjectException{
		for (Activity a : activities) {
			if (a.getMchine() ==m && a.getFeature() == f)
				return a;
		}
		throw new UnknownObjectException("The activity with machine " + m.getName() + " and feature " + f.getName() + 
										" does nto exist in job " + getName());
	} 

	public void deleteActivity(Activity a) {
		// TODO Auto-generated method stub
		
	}

	public void deleteFeature(String featureName) {
		// TODO Auto-generated method stub
		
	}
}
