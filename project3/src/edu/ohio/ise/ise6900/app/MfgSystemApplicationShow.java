package edu.ohio.ise.ise6900.app;

import java.util.*;


import edu.ohio.ise.ise6900.model.*;


public class MfgSystemApplicationShow {
	enum Command {
		JOB, MACHINE, ACTIVITY, FEATURE, STATE, // to create objects
		ACTIVITIES, FEATURES, STATES,           // to report collections for a given object
		DELETE, PRINTOUT, 						// to delete or printout an individual object
		JOBS, MACHINES, SYSTEM,					// to report system state and collections
		RECTANGLE, TRIANGLE, 					// to make draw objects
		EXIT, QUIT								// to exit the application
	} 
	static SortedMap<String, Command> commands;
	static String menu;

	static {
		commands = new TreeMap<String, Command>();
		commands.put("job", Command.JOB);
		commands.put("activity", Command.ACTIVITY);
		commands.put("feature", Command.FEATURE);
		commands.put("machine-state", Command.STATE);

		commands.put("delete", Command.DELETE);
		commands.put("printout", Command.PRINTOUT);

		commands.put("jobs", Command.JOBS);

		commands.put("rectangle", Command.RECTANGLE);

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
				switch (commandObj) {
				case JOB: {
					// create job
					try {
						String jobName = tokenizer.nextToken();
						int batchSize = Integer.parseInt(tokenizer.nextToken());
						ms.addJob(new Job(jobName, batchSize));
					} catch (AlreadyMemberException ex) {
						System.err.println(ex.getMessage());
					} catch (NumberFormatException nfe) {
						System.err.println("Batch size needs to be an integer!");
					} catch (NoSuchElementException e) {
						System.err.println("Not enough job parameters are specified");
					}
					break;
				}
				case FEATURE: {
					// create feature
					try {
						String featureName = tokenizer.nextToken();
						String jobName = tokenizer.nextToken();
						Job job = Job.findJob (jobName);
						job.addFeature(new MfgFeature(featureName));
					} catch (AlreadyMemberException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
					catch (NoSuchElementException e) {
						System.err.println("Not enough parameters for feature are specified! ");
					} catch (UnknownObjectException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
					break;
				}
				case ACTIVITIES: {
					try {
						String jobName = tokenizer.nextToken();
						Job j = ms.findJob(jobName);
						j.listActivities();
					} catch (UnknownObjectException e) {
						System.err.println(e.getMessage());
					} catch (NoSuchElementException e) {
						System.err.println("Job for activities listing needs to be specified! ");
					}
					break;
				}
				case DELETE: {
					System.err.println("The code for command " + commandObj + " is not implemented yet");
					break;
				}
				case PRINTOUT: {
					System.err.println("The code for command " + commandObj + " is not implemented yet");
					break;
				}
				case SYSTEM: {
					ms.printout();
					break;
				}
				case STATE:
				case STATES:
				case FEATURES:
				case RECTANGLE:
				case TRIANGLE:
				{
					System.err.println("Command " + commandObj + " is not implemented yet");
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

		}
		catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			// System.out.println("I am here");
			// e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		MfgSystemApplicationShow msa = new MfgSystemApplicationShow ();
		msa.run();
	}


}
