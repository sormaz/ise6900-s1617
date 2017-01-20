/**
 * 
 */
package edu.ohio.ise.ise6900.p2;

import java.util.Scanner;
import java.math.BigInteger;
import java.text.NumberFormat;
import static java.lang.Math.*;
/**
 * @author sormaz
 *
 */
public class CommandApplication {
	
	public static final int FACT_LIMIT_INT = 12;
	public static final int FACT_LIMIT_LONG = 20;
	public static final int MAX_CHOICE = 10;
	
	public static int factorial (int arg) {
		if (arg == 0) return 1;
		return arg * factorial (arg -1);
	}
	
	public static long longFactorial (long arg) {
		if (arg == 0) return 1;
		return arg * longFactorial (arg -1);
	}
	
	public static BigInteger bigFactorial (int arg) {
		if (arg == 0) return new BigInteger("1");
		return bigFactorial(arg -1).multiply(new BigInteger("" + arg));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String prompt = "\nEnter number of operation that you want to execute <type exit to end program>:\n" +
				"\t 1 sin(x)\n" + "\t 2 cos(x)\n" + "\t 3 tan(x)\n" + "\t 4 asin(x)\n" +
				"\t 5 acos(x)\n" + "\t 6 atan(x)\n" + "\t 7 ln(x)\n" + "\t 8 sqrt(x)\n" +
				"\t 9 power(x, e)\n" + "\t10 factorial(x)\n" +
				"Select->";
				String argPrompt = "Enter argument value (x):->";
				String expPrompt = "Enter exponent value (e):->";
				Scanner scanner = new Scanner (System.in);

				
				while (true) {
					System.out.print(prompt);
					String response = scanner.nextLine();
					if (response.equalsIgnoreCase("Exit") || 
							response.equalsIgnoreCase("Quit")) {
						break;
					}
					
					int choice;
					try {
						choice = Integer.parseInt(response);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
						System.err.println("\tEnter only numbers!!");
						continue;
					}
					if (choice >MAX_CHOICE || choice < 1) {
						System.err.println("\tThis choice is not supported!!");
						continue;
					}
					
					double myArg;
					int myIntArg = 0;
					while(true) {
						try {
							System.out.print(argPrompt);
							String argString = scanner.nextLine();
							myArg = Double.parseDouble(argString);
							if ((choice == 4 || choice == 5) && abs(myArg) > 1) {
								System.err.println("\tThe argument has to be between -1 and 1!!");
								continue;
							}
							if (choice == 7 && myArg <= 0) {
								System.err.println("\tThe argument has to be positive number");
								continue;
							}
							if (choice == 8 && myArg <0) {
								System.err.println("\tThe argument has to be non-negative number");
								continue;
							}
							if (choice == 10) {
								String factErrorMessage = "\tEnter only non-negative integer as argument for factorial!!";
								try {
									myIntArg = Integer.parseInt(argString);
									if (myIntArg < 0) {
										System.err.println(factErrorMessage);
										continue;
									}
								} catch (NumberFormatException e) {
									System.err.println(factErrorMessage);
									continue;
								}
							}
							break;
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							System.err.println("\tEnter only numbers for argument!!");
						}
						
					}
					double result = Double.NaN;
					String resultString = "";
					int resultInt = 0;
					switch (choice) {
						case 1:
							result = sin(myArg * PI/180);

							break;
						case 2:
							result = cos(myArg * PI/180);
							break;
						case 3:
							result = tan(myArg * PI/180);
							break;
						case 4:
							result = asin(myArg) *  180 /PI;
							break;
						case 5:
							result = acos(myArg) *  180 /PI;
							break;
						case 6:
							result = atan(myArg) *  180 /PI;
							break;
						case 7:
							result = log(myArg);
							break;
						case 8:
							result = sqrt(myArg);
							break;
						case 9:
							double exponent;
							System.out.print(expPrompt);
							while (true) {
								String expString = scanner.nextLine();
								try {
									exponent = Double.parseDouble(expString);
									break;
								} catch (NumberFormatException e) {
									// TODO Auto-generated catch block
									System.err.println("Enter numbers only for exponent!");
								}						
							}
							result = pow(myArg, exponent);
							if (Double.isNaN(result)) {
								resultString = "\tYour arguments do not give the correct value. Try again!";
							}
							break;
						case 10:
							
							if (myArg < FACT_LIMIT_INT){
								resultInt = factorial(myIntArg);
								resultString = Integer.toString(resultInt);
							} else 
							if (myArg < FACT_LIMIT_LONG) {
								resultString = Long.toString(longFactorial(myIntArg));
								
							}
							else {
								resultString = bigFactorial(myIntArg).toString();
							}
								
							break;
						default:
							System.err.println("\tThis choice is not supported!!");
							break;
					}
					if (!Double.isNaN(result)) {
					NumberFormat format = NumberFormat.getInstance();
					resultString = "" + result; //format.format(result);
					}
					System.out.println ("Result is: " + resultString);
					
				}
			}

	}


