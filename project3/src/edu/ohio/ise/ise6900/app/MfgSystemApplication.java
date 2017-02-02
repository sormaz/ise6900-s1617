package edu.ohio.ise.ise6900.app;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import edu.ohio.ise.ise6900.model.*;


public class MfgSystemApplication {
	enum Commands { JOB, MACHINE, ACTIVITY, JOBS, EXIT, QUIT}
	static HashMap<String, Commands> commands;
	static final int OK = 0;
	static String menu;

	
	static {
		commands = new HashMap<String, Commands>(); 
		commands.put("job", Commands.JOB);
//		commands.put("machine", MACHINE);
//		commands.put("undir-arc", UNDIR_ARC);
//		commands.put("delete", DELETE);
//		commands.put("printout", PRINTOUT);
//		commands.put("graph", GRAPH);
		commands.put("jobs", Commands.JOBS);
//		commands.put("arcs", ARCS);
		commands.put("exit", Commands.EXIT);
		commands.put("quit", Commands.QUIT);
		
		menu = "\nOptions : \n\t" + commands.keySet().toString() + "\nEnter the command:->";
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		StringTokenizer tokenizer; 
		MfgSystem ms = new MfgSystem("test");
//		String input;
		try 
		{
			while (true) 
			{
				System.err.flush();
//				System.out.print(menu);
				System.out.print(menu);

				String input = sc.nextLine();
				System.out.println("input is: " + input);
				
				tokenizer = new StringTokenizer(input);
				String commandString;
				try {
					commandString = tokenizer.nextToken();
				} catch (Exception e1) {
					System.err.println ("No input specified");
					continue;
				}
				Commands commandObj = commands.get(commandString.toLowerCase());
				if (commandObj == null) {
					System.err.println("Your command '" 
								+ commandString + "' is not supported");
					continue;
				}
//				int command = commandObj;
				switch (commandObj) {
				case JOB: {
					// make node
					try {
						String jobName = tokenizer.nextToken();
						int batchSize = Integer.parseInt(tokenizer.nextToken());
						ms.addJob(new Job (jobName, batchSize));
					} catch (AlreadyMemberException ex) {
						System.err.println (ex.getMessage());
					} catch (NumberFormatException nfe) {
						System.err.println("Batch size needs to be an integer!");
					}
					 catch (NoSuchElementException e) {
						System.err.println ("Not enough job parameters specified");
					}
					break;
				}
//				case DIR_ARC: {
//					// make arc
//					try {
//						String parent = tokenizer.nextToken();
//						String child = tokenizer.nextToken();
//						String valueString = tokenizer.nextToken();
//						double val = Double.parseDouble(valueString);
//
//							try {
//								Node p = this.findNode(parent);
//								Node c = this.findNode(child);
//								this.addDirectedArc(parent, child, val);
////								p.addDirectedArc (this.findNode(child));
//							} catch (GraphException e) {
//								System.err.println
//									(e.getMessage());
//								continue;
//							}
//						
//					} catch (NoSuchElementException e) {
//						System.err.println 
//						("Two node parameter objects should be specified: " + input);
//					}
//					break;
//				}
//				case UNDIR_ARC: {
//					// make arc
//					try {
//						String user1 = tokenizer.nextToken();
//						String user2 = tokenizer.nextToken();
//						try {
//							Node n = this.findNode(user1);
//							Node n2 = this.findNode(user2);
//							this.addUndirectedArc(user1, user2);
////							n.addUndirectedArc (this.findNode (user2));
//						}
//						catch (GraphException e) {
//							System.err.println
//								(e.getMessage());
//							continue;
//						}
//					}
//					catch (NoSuchElementException e) {
//						System.err.println 
//						("Two node parameter objects should be specified");
//					}
//					break;
//				}
//				case DELETE: {
//					String arg1 = tokenizer.nextToken();
//					if (tokenizer.hasMoreTokens()) {
//						String arg2 = tokenizer.nextToken();
//						try {
//							deleteObjectArc(arg1, arg2);
//						}
//						catch (GraphException e) {
//							System.err.println(e.getMessage());
//						}
//					}
//					else {
//						// delete node
//						
//						if (!this.deleteObject(arg1)) {
//							System.err.println ("Your parameter is not in the graph");
//						}
//					}				
//					break;
//				}
//				case PRINTOUT: {
//					// printout chosen object
//					break;
//				}
//				case GRAPH: {
//					// printout the graph
//					this.printout ();
//					break;
//				}
				case JOBS: {			
					ms.printJobes();
					break;
				}
//				case ARCS: {
//					System.out.println(this.printArcs());
//					break;
//				}
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
//			System.out.println("I am here");
//			e.printStackTrace();
		}	

	}

}
