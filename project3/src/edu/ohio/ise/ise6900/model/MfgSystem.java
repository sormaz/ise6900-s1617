
package edu.ohio.ise.ise6900.model;


import java.io.*;
import java.util.*;

import static edu.ohio.ise.ise6900.app.MfgSystemApplication.*;
import edu.ohio.ise.ise6900.gui.Rectangle;
import edu.ohio.ise.ise6900.gui.Triangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class MfgSystem extends MfgObject {
	
	static Properties properties;
	
	Map<String,Job> jobs;
	Map<String,Machine> machines;
	
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
	

	public MfgSystem(String n) {
		super(n);
		jobs = new HashMap<String,Job>();
		machines = new HashMap<String,Machine>();
	}
	public MfgSystem (File f) throws FileNotFoundException {
		this(f.getName());
		read(f);
	}
	
	public void addJob(Job j) throws AlreadyMemberException {
		try {
			findJob(j.getName());
			throw new AlreadyMemberException("Job " + j.getName() + " is alreaady in the mfg system " + this.getName());
		} catch (UnknownObjectException e) {
			jobs.put(j.getName(), j);	
		}
	}

	public Job findJob(String jobName) throws UnknownObjectException{
		Job job =  jobs.get(jobName);
		if (job == null)
			throw new UnknownObjectException("job with name " + jobName + " does not exist.");
		return job;
	}

	public void printJobs() {
		for (Job j : jobs.values()) {
			System.out.println(j.toString());
		}
		
	}

	public void addMachine(Machine m) throws AlreadyMemberException {
		try {
			findMachine(m.getName());
			throw new AlreadyMemberException("Machine " + m.getName() + " is alreaady in the mfg system " + this);
		} catch (UnknownObjectException e) {
			machines.put(m.getName(), m);		
		}	
	}

	public void printMachines() {
		for (Machine m : machines.values()) {
			System.out.println(m.toString());
		}
		
	}
	
	public Machine findMachine(String machineName) throws UnknownObjectException{
		Machine machine =  machines.get(machineName);
		if (machine == null)
			throw new UnknownObjectException("Machine with name " + machineName + " does not exist.");
		return machine;
	}
	
	public String toString () {
		return "MfgSystem " + getName() + "contains " + jobs.size() + " Jobs and "  + machines.size() + " machines";
		
	}
	
	public void printout () {
		System.out.println(toString());
	}

	public void deleteJob(String name) throws UnknownObjectException{
		
		Job j = jobs.remove(name);
		if (j == null) {
			throw new UnknownObjectException ("Job " + name + " did not exist in the mfg system " + getName());
		}
		
	}
	
	public void deleteMachine(String name) throws UnknownObjectException{
		
		Machine m = machines.remove(name);
		if (m == null) {
			throw new UnknownObjectException ("Machine " + name + " did not exist in the mfg system " + getName());
		}
		
	}
	
	public void read (File f) throws FileNotFoundException {
		read (new BufferedInputStream (new FileInputStream(f)));
	}
	
	public  void read (InputStream is) {
		
		Scanner sc = new Scanner(is);
		StringTokenizer tokenizer;
		
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
						addJob(new Job(jobName, batchSize));
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

						addMachine(new Machine(machineName));
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
						Job job = findJob (jobName);
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
						Machine m = findMachine(machName);
						Job j = findJob(jobName);
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
						Job j = findJob(jobName);
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
							Machine m = findMachine(machName);
							Job j = findJob(jobName);
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
							Job job = findJob (jobName);
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
							deleteJob (name);
							System.out.println("Job " + name + " is deleted!" );
						}
						catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
							try {
								deleteMachine(name);
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
							Machine m = findMachine(machName);
							Job j = findJob(jobName);
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
							Job job = findJob (jobName);
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
							Job j = findJob (name);
							j.printout();
						}
						catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
							try {
								Machine m = findMachine(name);
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
					printout();
					break;
				}
				case STATE: {
					String stateType = null;
					try {
						String machName = tokenizer.nextToken();
						stateType = tokenizer.nextToken();
						double start = Double.parseDouble(tokenizer.nextToken());
						double end = Double.parseDouble(tokenizer.nextToken());
						Machine m = findMachine(machName);
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
						Machine m = findMachine (machineName);
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
						Job j = findJob(jobName);
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
					printJobs();
					break;
				}
				case MACHINES: {
					printMachines();
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
							Machine m = findMachine(machName);
							Job j = findJob(jobName);
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
							Job job = findJob (jobName);
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
							Job j = findJob (name);
							j.display(null);
						}
						catch (UnknownObjectException e) {
							errStream.println(e.getMessage());
							try {
								Machine m = findMachine(name);
								m.display(null);
							} catch (UnknownObjectException e1) {
								errStream.println(e1.getMessage());
							}	
						}
						break;
					}
					case 0: {
						display(null);
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
	public List<Machine> getMachines() {
		// TODO Auto-generated method stub
		 return new ArrayList(machines.values());
	}
	
	public Collection<Job> getJobs() {
		// TODO Auto-generated method stub
		return jobs.values();
	}
	
	@Override
	public Collection<Shape> makeShapes() {
		Collection<Shape> shapes = new ArrayList<Shape>();
		for (Machine m : machines.values()) {
		shapes.addAll(m.makeShapes());
		}
		return shapes;
	}
	
	public static void main (String [] args) {
		MfgSystem ms = new MfgSystem("");
		try {
			ms.read(new File ("fss-demo-pr3a.mfg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
