package edu.ohio.ise.ise6900.model;

public enum Command {
	JOB, MACHINE, ACTIVITY, FEATURE, STATE, // to create objects
	ACTIVITIES, FEATURES, STATES,           // to report collections for a given object
	DELETE, PRINTOUT, 						// to delete or printout an individual object
	JOBS, MACHINES, SYSTEM,					// to report system state and collections
	RECTANGLE, TRIANGLE, 					// to make draw objects
	EXIT, QUIT								// to exit the application
}
