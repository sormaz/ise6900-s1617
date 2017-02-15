package edu.ohio.ise.ise6900.model;

public class MfgFeature extends MfgObject {
	
	Job job;
	Activity activity;

	public MfgFeature(String n) {
		super(n);

	}
	
	@Override
	public void printout() {
		throw new UnsupportedOperationException("Method printout() not implemented yet");
		
	}

}
