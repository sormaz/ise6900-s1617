package edu.ohio.ise.ise6900.model;

import java.util.HashMap;
import java.util.Map;

public abstract class MfgObject {
	
	public static Map<String, MfgObject> objectMap = new HashMap<String, MfgObject> ();
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
	
	public abstract void printout ();
	
}
