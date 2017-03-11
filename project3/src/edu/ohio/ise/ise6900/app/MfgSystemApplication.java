
package edu.ohio.ise.ise6900.app;

import java.io.PrintStream;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.ohio.ise.ise6900.gui.*;
import edu.ohio.ise.ise6900.model.*;

public class MfgSystemApplication {


	public static SortedMap<String, Command> commands;
	static final int OK = 0;
	public static String menu;

	static {
		commands = new TreeMap<String, Command>();
		commands.put("job", Command.JOB);
//		commands.put("ish", Command.JOB);
//		commands.put("zadatak", Command.JOB);
		commands.put("machine", Command.MACHINE);
		commands.put("activity", Command.ACTIVITY);
		commands.put("feature", Command.FEATURE);
		commands.put("machine-state", Command.STATE);

		commands.put("activities", Command.ACTIVITIES);
		commands.put("features", Command.FEATURES);
		commands.put("states", Command.STATES);

		commands.put("delete", Command.DELETE);
		commands.put("printout", Command.PRINTOUT);
		commands.put("display", Command.DISPLAY);

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
		PrintStream errStream = System.err;
		// String input;
		try {
			while (true) {
				errStream.flush();
				// System.out.print(menu);
				System.out.print(menu);

				String input = sc.nextLine();
				System.out.println("input is: " + input);

				tokenizer = new StringTokenizer(input);
				String commandString;
				
				// read command, if aempty line loop silently 
				try {
					commandString = tokenizer.nextToken();
				} catch (Exception e1) {
//					 errStream.println("No input specified");
					continue;
				}
				// check for comment - starts with #, if comment line, loop silently 
				if (commandString.startsWith("#")) {
					continue;
				}
				Command commandObj = commands.get(commandString.toLowerCase());
				if (commandObj == null) {
					errStream.println("Your command '" + commandString + "' is not supported");
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
						errStream.println(ex.getMessage());
					} catch (NumberFormatException nfe) {

						errStream.println("Batch size needs to be an integer!");

					} catch (NoSuchElementException e) {
						errStream.println("Not enough job parameters are specified");
					}
					break;
				}
				case MACHINE: {
					// create machine
					try {
						String machineName = tokenizer.nextToken();

						ms.addMachine(new Machine(machineName));
						// p.addDirectedArc (this.findNode(child));
					} catch (AlreadyMemberException ex) {
						errStream.println(ex.getMessage());
					}
					catch (NoSuchElementException e) {
						errStream.println("No machine parameter is specified! ");
					}
					break;
				}
				case FEATURE: {
					// create feature
					try {
						String featureName = tokenizer.nextToken();
						String jobName = tokenizer.nextToken();
						Job job = ms.findJob (jobName);
						job.addFeature(new MfgFeature(featureName));
					} catch (AlreadyMemberException e) {
						// TODO Auto-generated catch block
						errStream.println(e.getMessage());
					}
					catch (NoSuchElementException e) {
						errStream.println("Not enough parameters for feature are specified! ");
					} catch (UnknownObjectException e) {
						// TODO Auto-generated catch block
						errStream.println(e.getMessage());
					}
					break;
				}
				case ACTIVITY: {
					try {
						String machName = tokenizer.nextToken();
						String jobName = tokenizer.nextToken();
						String featureName = tokenizer.nextToken();
						double start = Double.parseDouble(tokenizer.nextToken());
						double end = Double.parseDouble(tokenizer.nextToken());
						Machine m = ms.findMachine(machName);
						Job j = ms.findJob(jobName);
						MfgFeature f = j.findFeature(featureName);
						Activity a = new Activity(m, j, f, start, end);
						m.addState(a);
						j.addActivity (a);
					} catch (NumberFormatException e) {
						errStream.println("Start time and End time need to be numbers!");
					} catch (UnknownObjectException e) {
						errStream.println(e.getMessage());
					} catch (AlreadyMemberException e) {

						errStream.println(e.getMessage());
					} catch (IllegalArgumentException e) {

						errStream.println(e.getMessage());

					}
					break;
				}
				case ACTIVITIES: {
					try {
						String jobName = tokenizer.nextToken();
						Job j = ms.findJob(jobName);
						j.listActivities();
					} catch (UnknownObjectException e) {
						errStream.println(e.getMessage());
					} catch (NoSuchElementException e) {
						errStream.println("Job for activities listing needs to be specified! ");
					}
					break;
				}
				case DELETE: {
					int numPars = tokenizer.countTokens();
					switch (numPars) {
					case 3: {
						// delete activity, format delete m1, j1, f1
						try {
							String machName = tokenizer.nextToken();
							String jobName = tokenizer.nextToken();
							String featureName = tokenizer.nextToken();
							Machine m = ms.findMachine(machName);
							Job j = ms.findJob(jobName);
							MfgFeature f = j.findFeature(featureName);
							Activity a = j.findActivity(m, f);
							j.deleteActivity (a);
							m.deleteState(a);							
						} catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
						}
						break;
					}
					case 2: {
						// delete feature, format delete f1 j1
						try {
							String featureName = tokenizer.nextToken();
							String jobName = tokenizer.nextToken();
							Job job = ms.findJob (jobName);
							job.deleteFeature(featureName);
						} catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
						}
						break;
					}
					case 1: {
						// delete machine or job, try job first
						// format delete j1 or delete m1
						String name = tokenizer.nextToken();
						try {
							ms.deleteJob (name);
							System.out.println("Job " + name + " is deleted!" );
						}
						catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
							try {
								ms.deleteMachine(name);
								System.out.println("Machine " + name + " is deleted!" );
							} catch (UnknownObjectException e1) {
								errStream.println(e1.getMessage());
							}	
						}
						break;
					}
					default:
					errStream.println("Command " + commandObj + " requires 1, 2, or 3 arguments");
					}
					break;
				}
				case PRINTOUT: {
					int numPars = tokenizer.countTokens();
					switch (numPars) {
					case 3: {
						// delete activity, format delete m1, j1, f1
						try {
							String machName = tokenizer.nextToken();
							String jobName = tokenizer.nextToken();
							String featureName = tokenizer.nextToken();
							Machine m = ms.findMachine(machName);
							Job j = ms.findJob(jobName);
							MfgFeature f = j.findFeature(featureName);
							Activity a = j.findActivity(m, f);
							a.printout();						
						} catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
						}
						break;
					}
					case 2: {
						// delete feature, format delete f1 j1
						try {
							String featureName = tokenizer.nextToken();
							String jobName = tokenizer.nextToken();
							Job job = ms.findJob (jobName);
							MfgFeature f = job.findFeature(featureName);
							f.printout();
						} catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
						}
						break;
					}
					case 1: {
						// delete machine or job, try job first
						// format delete j1 or delete m1
						String name = tokenizer.nextToken();
						try {
							Job j = ms.findJob (name);
							j.printout();
						}
						catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
							try {
								Machine m = ms.findMachine(name);
								m.printout();
							} catch (UnknownObjectException e1) {
								errStream.println(e1.getMessage());
							}	
						}
						break;
					}
					default:
					errStream.println("Command " + commandObj + " requires 1, 2, or 3 arguments");
					}
					break;
				}
				case SYSTEM: {
					ms.printout();
					break;
				}
				case STATE: {
					String stateType = null;
					try {
						String machName = tokenizer.nextToken();
						stateType = tokenizer.nextToken();
						double start = Double.parseDouble(tokenizer.nextToken());
						double end = Double.parseDouble(tokenizer.nextToken());
						Machine m = ms.findMachine(machName);
						MachineState mst = new MachineState(m, stateType.toUpperCase(), start, end);
						m.addState(mst);
					} catch (NumberFormatException e) {
						errStream.println("Start time and End time need to be numbers!");
					} catch (UnknownObjectException e) {
						errStream.println(e.getMessage());
					} catch (IllegalArgumentException e) {
						errStream.println("State type " + stateType + " is not defined" );
					}
//					catch (AlreadyMemberException e) {
//						errStream.println(e.getMessage());
//					}

					break;
				}
				case STATES: {
					try {
						String machineName = tokenizer.nextToken();
						Machine m = ms.findMachine (machineName);
						m.listStates();
					} catch (UnknownObjectException e) {
						errStream.println(e.getMessage());
					} catch (NoSuchElementException e) {
						errStream.println("Machine for states listing needs to be specified! ");
					}
					break;
				}

				case FEATURES: {
					try {
						String jobName = tokenizer.nextToken();
						Job j = ms.findJob(jobName);
						j.listFeatures();
					} catch (UnknownObjectException e) {
						errStream.println(e.getMessage());
					} catch (NoSuchElementException e) {
						errStream.println("Job for features listing needs to be specified! ");
					}
					break;
				}

				case RECTANGLE: {
					try {
					double x = Double.parseDouble(tokenizer.nextToken());
					double y = Double.parseDouble(tokenizer.nextToken());
					double w = Double.parseDouble(tokenizer.nextToken());
					double h = Double.parseDouble(tokenizer.nextToken());
					Rectangle r = new Rectangle(x,y,w,h);
					}
					catch (NoSuchElementException e) {
						errStream.println("Job for features listing needs to be specified! ");
					}
					break;
				}
				case TRIANGLE:
				{
					try {
					double x = Double.parseDouble(tokenizer.nextToken());
					double y = Double.parseDouble(tokenizer.nextToken());
					double b = Double.parseDouble(tokenizer.nextToken());
					double h = Double.parseDouble(tokenizer.nextToken());
					Triangle r = new Triangle(x,y,b,h);
					}
					catch (NoSuchElementException e) {
						errStream.println("Job for features listing needs to be specified! ");
					}
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
				
				case DISPLAY: {
					int numPars = tokenizer.countTokens();
					switch (numPars) {
					case 3: {
						// delete activity, format delete m1, j1, f1
						try {
							String machName = tokenizer.nextToken();
							String jobName = tokenizer.nextToken();
							String featureName = tokenizer.nextToken();
							Machine m = ms.findMachine(machName);
							Job j = ms.findJob(jobName);
							MfgFeature f = j.findFeature(featureName);
							Activity a = j.findActivity(m, f);
							a.display(null);						
						} catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
						}
						break;
					}
					case 2: {
						// delete feature, format delete f1 j1
						try {
							String featureName = tokenizer.nextToken();
							String jobName = tokenizer.nextToken();
							Job job = ms.findJob (jobName);
							MfgFeature f = job.findFeature(featureName);
							f.display(null);
						} catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
						}
						break;
					}
					case 1: {
						// delete machine or job, try job first
						// format delete j1 or delete m1
						String name = tokenizer.nextToken();
						try {
							Job j = ms.findJob (name);
							j.display(null);
						}
						catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
							try {
								Machine m = ms.findMachine(name);
								m.display(null);
							} catch (UnknownObjectException e1) {
								errStream.println(e1.getMessage());
							}	
						}
						break;
					}
					default:
					errStream.println("Command " + commandObj + " requires 1, 2, or 3 arguments");
					}
					break;
				}
				case QUIT:
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

 

