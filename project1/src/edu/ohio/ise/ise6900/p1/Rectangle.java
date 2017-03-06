package edu.ohio.ise.ise6900.p1;

import java.util.Scanner;

public class Rectangle {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		double width, height;
		
		// get rectangle parameters
		System.out.print("Enter width:");
		width = s.nextDouble();
		System.out.print("Enter height:");
		height = s.nextDouble();
		
		// Calculate area, perimeter, and teh area on circumscribed circle]
		double area = width * height;
		double perimeter = 2 * (width + height);
		double diagonal = Math.sqrt(width * width + height * height);
		double circleArea = diagonal * diagonal  * Math.PI / 4;
		s.close();
		// Print out the results
		System.out.println("Rectangle parameters: width=" + width + ", height=" + height);
		System.out.println("Area=" + area);
		System.out.println("Perimeter=" + perimeter);
		System.out.println("Area of the circumscribed circle=" + circleArea);
	}
}
