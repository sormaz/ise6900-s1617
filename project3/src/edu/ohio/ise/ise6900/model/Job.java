package edu.ohio.ise.ise6900.model;

public class Job extends MfgObject {
	
	private int batchSize;

	public Job(String n, int batchSize) {
		super(n);
		this.batchSize = batchSize;
	}

}
