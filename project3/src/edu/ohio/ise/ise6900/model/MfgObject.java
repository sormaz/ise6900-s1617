package edu.ohio.ise.ise6900.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import edu.ohio.ise.ise6900.draw.DrawApplication;
import edu.ohio.ise.ise6900.draw.Drawable;

public abstract class MfgObject implements Drawable {
	
	static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("mfgsystem.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
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
	
	public void display (String [] args) {
		DrawApplication da = new DrawApplication();
		da.getCanvas().addTarget(this);
		da.main(args);
	}
	
	public static String getProperty(String name, String def) {
		return properties.getProperty(name,def);
	}
	
}
