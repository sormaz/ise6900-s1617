package edu.ohio.ise.ise6900.p1;

import java.util.Scanner;

public class LoopTester {

	public static void main(String[] args) {
	Scanner me =new Scanner(System.in);
	int i;
	double s = 0;
	for (i=0; i<4; i++) {
	System.out.println("Enter a number");
	int a = me.nextInt();
	s +=a;

	}
	double ave = s/i;
	System.out.println("The sum of the numbers is = " + s); 
	System.out.println("The average of the numbers is = " + ave); 
	}

	}
