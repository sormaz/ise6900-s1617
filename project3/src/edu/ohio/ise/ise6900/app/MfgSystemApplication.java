package edu.ohio.ise.ise6900.app;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.ohio.ise.ise6900.model.*;

public class MfgSystemApplication {
	enum Command {
		JOB, MACHINE, ACTIVITY, JOBS, MACHINES, ACTIVITIES, EXIT, QUIT
	}

	static HashMap<String, Command> commands;
	static final int OK = 0;
	static String menu;

	static {
		commands = new HashMap<String, Command>();
		commands.put("job", Command.JOB);
		commands.put("machine", Command.MACHINE);
		commands.put("activity", Command.ACTIVITY);
		// commands.put("delete", DELETE);
		// commands.put("printout", PRINTOUT);
		// commands.put("graph", GRAPH);
		commands.put("jobs", Command.JOBS);
		commands.put("machines", Command.MACHINES);
		commands.put("exit", Command.EXIT);
		commands.put("quit", Command.QUIT);

		menu = "\nOptions : \n\t" + commands.keySet().toString() + "\nEnter the command:->";
	}

	public static void main(String[] args) {
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
				// case UNDIR_ARC: {
				// // make arc
				// try {
				// String user1 = tokenizer.nextToken();
				// String user2 = tokenizer.nextToken();
				// try {
				// Node n = this.findNode(user1);
				// Node n2 = this.findNode(user2);
				// this.addUndirectedArc(user1, user2);
				//// n.addUndirectedArc (this.findNode (user2));
				// }
				// catch (GraphException e) {
				// System.err.println
				// (e.getMessage());
				// continue;
				// }
				// }
				// catch (NoSuchElementException e) {
				// System.err.println
				// ("Two node parameter objects should be specified");
				// }
				// break;
				// }
				// case DELETE: {
				// String arg1 = tokenizer.nextToken();
				// if (tokenizer.hasMoreTokens()) {
				// String arg2 = tokenizer.nextToken();
				// try {
				// deleteObjectArc(arg1, arg2);
				// }
				// catch (GraphException e) {
				// System.err.println(e.getMessage());
				// }
				// }
				// else {
				// // delete node
				//
				// if (!this.deleteObject(arg1)) {
				// System.err.println ("Your parameter is not in the graph");
				// }
				// }
				// break;
				// }
				// case PRINTOUT: {
				// // printout chosen object
				// break;
				// }
				// case GRAPH: {
				// // printout the graph
				// this.printout ();
				// break;
				// }
				case JOBS: {
					ms.printJobes();
					break;
				}
				// case ARCS: {
				// System.out.println(this.printArcs());
				// break;
				// }
				case EXIT:
				case QUIT: {
					// exit the program
					System.exit(OK);
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

}
