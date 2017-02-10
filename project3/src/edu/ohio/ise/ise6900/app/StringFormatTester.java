package edu.ohio.ise.ise6900.app;

public class StringFormatTester {

	public static void main(String[] args) {

		 String result = String.format("One: %1$d Two: %2$.3f Three: %3$.2f",
	                10, 20.55, 30.448);
	        System.out.println(result);
	}

}
