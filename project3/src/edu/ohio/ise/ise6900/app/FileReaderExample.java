package edu.ohio.ise.ise6900.app;

import java.io.*;
import java.util.Scanner;

public class FileReaderExample {

	public FileReaderExample() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		InputStream is;
		if (args.length >0) {
			File f = new File (args[0]);
			try {
				FileInputStream fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis);
				is = bis;
			} catch (FileNotFoundException e) {
				System.out.println("File " + f + " can not be found, rading from Keyboard");
				is = System.in;
			}

		}
		else {
			is = System.in;			
		}
		Scanner s = new Scanner (is);
		while (true) {
			String str = s.nextLine();
			if (str == null || str.length() == 0) {
				break;	
			}
			System.out.println(" Next line in " + is.toString() + " is\n\t" + str);

		}



	}

}
