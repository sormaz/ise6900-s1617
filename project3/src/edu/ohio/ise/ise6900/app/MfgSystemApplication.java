package edu.ohio.ise.ise6900.app;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.ohio.ise.ise6900.model.*;

public class MfgSystemApplication {
	enum Command {
		JOB, MACHINE, ACTIVITY, FEATURE, STATE, // to create objects
		ACTIVITIES, FEATURES, STATES,           // to report collections for a given object
		DELETE, PRINTOUT, 						// to delete or printout an individual object
		JOBS, MACHINES, SYSTEM,					// to report system state and collections
		RECTANGLE, TRIANGLE, 					// to make draw objects
		EXIT, QUIT								// to exit the application
	} 

	static SortedMap<String, Command> commands;
	static final int OK = 0;
	static String menu;

	static {
		commands = new TreeMap<String, Command>();
		commands.put("job", Command.JOB);
		commands.put("ish", Command.JOB);
		commands.put("zadatak", Command.JOB);
		commands.put("machine", Command.MACHINE);
		commands.put("activity", Command.ACTIVITY);
		commands.put("feature", Command.FEATURE);
		commands.put("machine-state", Command.STATE);
		
		commands.put("activities", Command.ACTIVITIES);
		commands.put("features", Command.FEATURES);
		commands.put("states", Command.STATES);

		commands.put("delete", Command.DELETE);
		commands.put("printout", Command.PRINTOUT);

		commands.put("jobs", Command.JOBS);
		commands.put("machines", Command.MACHINES);
		commands.put("system", Command.SYSTEM);
		
		commands.put("rectangle", Command.RECTANGLE);
		commands.put("triangle", Command.TRIANGLE);
		
		commands.put("exit", Command.EXIT);
		commands.put("quit", Command.EXIT);

		menu = "\nOptions : \n\t" + commands.keySet().toString() + "\nEnter the command:->";
	}

	public  void run() {
		Scanner sc = new Scanner(System.in);
		StringTokenizer tokenizer;
		MfgSystem ms = new MfgSystem("test");
		// String input;
		try {
			while (true) {
				System.err.flush();
				// System.out.print(menu);
				System.out.print(menu);

				String input = sc.nextLine();
				System.out.println("input is: " + input);

				tokenizer = new StringTokenizer(input);
				String commandString;
				try {
					commandString = tokenizer.nextToken();
				} catch (Exception e1) {
					System.err.println("No input specified");
					continue;
				}
				Command commandObj = commands.get(commandString.toLowerCase());
				if (commandObj == null) {
					System.err.println("Your command '" + commandString + "' is not supported");
					continue;
				}
				// int command = commandObj;
				switch (commandObj) {
				case JOB: {
					// make node
					try {
						String jobName = tokenizer.nextToken();
						int batchSize = Integer.parseInt(tokenizer.nextToken());
						ms.addJob(new Job(jobName, batchSize));
					} catch (AlreadyMemberException ex) {
						System.err.println(ex.getMessage());
					} catch (NumberFormatException nfe) {
						System.err.println("Batch size needs to be an integer!");
					} catch (NoSuchElementException e) {
						System.err.println("Not enough job parameters specified");
					}
					break;
				}
				case MACHINE: {
					// make arc
					try {
						String machineName = tokenizer.nextToken();

						ms.addMachine(new Machine(machineName));
						// p.addDirectedArc (this.findNode(child));
					} catch (AlreadyMemberException ex) {
						System.err.println(ex.getMessage());

					}

					catch (NoSuchElementException e) {
						System.err.println("No machine parameter is specified! ");
					}
					break;
				}
				 case FEATURE: {
					 String featureName = tokenizer.nextToken();
					 String jobName = tokenizer.nextToken();
					 System.err.println("The code for command " + commandString + " is not completed yet");
				 break;
				 }
				 case DELETE: {
					 System.err.println("The code for command " + commandString + " is not implemeneted yet");
				 break;
				 }
				 case PRINTOUT: {
					 System.err.println("The code for command " + commandString + " is not implemeneted yet");
				 break;
				 }

				case ACTIVITIES: 
				case ACTIVITY:
				case STATE:
				case FEATURES:
				case SYSTEM:
				case RECTANGLE:
				case TRIANGLE:
				 {
					 System.err.println("Command " + commandString + " is not implemeneted yet");
				 break;
				 }
				case JOBS: {
					ms.printJobs();
					break;
				}
				 case MACHINES: {
				 ms.printMachines();
				 break;
				 }
				case EXIT: {
					// exit the program
					return;
				}

				}
			}
			// need to check for duplicate nodes
		}

		catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			// System.out.println("I am here");
			// e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		MfgSystemApplication msa = new MfgSystemApplication ();
		msa.run();
	}

}

