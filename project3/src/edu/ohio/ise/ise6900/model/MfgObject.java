package edu.ohio.ise.ise6900.model;

public abstract class MfgObject {
	private String name;
	
	public MfgObject (String n) {
		name = n;
	}
	
	public String getName() {
		
		return name;
	}
	
	public String toString() {
		return name;
	}

}
