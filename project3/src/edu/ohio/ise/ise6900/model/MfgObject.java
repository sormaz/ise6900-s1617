package edu.ohio.ise.ise6900.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import edu.ohio.ise.ise6900.draw.DrawApplication;
import edu.ohio.ise.ise6900.draw.Drawable;

public abstract class MfgObject implements Drawable {
	
	static DrawApplication da = new DrawApplication();
	static Properties properties;
	
	static public double OFFSET = 0.0;
	static public double SCALE = 1.0;
	static public double HEIGHT = 10.0;
	
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("mfgsystem.properties")));
			try {
				SCALE = Double.parseDouble(getProperty("SCALE", Double.toString(SCALE)));
			} catch (NumberFormatException e) {
				// Can not read values keep default
				System.err.println (e.getMessage());
			}
			try {
				OFFSET = Double.parseDouble(getProperty("OFFSET", Double.toString(OFFSET)));
			} catch (NumberFormatException e) {
				// Can not read values keep default
				System.err.println (e.getMessage());
			}
			try {
				HEIGHT = Double.parseDouble(getProperty("HEIGHT", Double.toString(HEIGHT)));
			} catch (NumberFormatException e) {
				// Can not read values keep default
				System.err.println (e.getMessage());
			}
		} catch (FileNotFoundException e) {
			// Can not read properties, keep defaults
			System.err.println (e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println (e.getMessage());
		} 
	}
	
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
	
	public void display (String [] args) throws InvocationTargetException {
		da.getCanvas().getChildren().clear();
		da.getCanvas().clear();
		da.getCanvas().addTarget(this);
//		try {
			try {
				da.main(args);
			} catch (Exception e) {
				throw new InvocationTargetException(e, "error");
			}
//		} catch (Exception e) {
//			// this does not work still thread problem
//			da.show();
//		}

	}
	
	public static String getProperty(String name, String def) {
		return properties.getProperty(name,def);
	}
	
}
