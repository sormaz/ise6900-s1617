package edu.ohio.ise.ise6900.p2;

public class StringSwitcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String names[] = { "a", "b", "c", "d" };
		for (String n : names) {
			switch (n) {
			case "a": {
				System.out.println("\nfound \n\t" + n);
				break;
			}
			case "b": {
				System.out.println("found\\ " + n);
				break;
			}
			case "c": {
				System.out.println("found\" " + n);
				break;
			}
			default:
				System.out.println("found\r " + n);
				break;

			}

		}
	}

}
