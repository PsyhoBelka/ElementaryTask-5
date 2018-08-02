package ua.rozhkov;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.print("Input number (max 999 999 999): ");
		String input = new Scanner(System.in).nextLine();
		long number;
		try {
			number = Long.parseUnsignedLong(input);
		}
		catch (NumberFormatException e) {
			System.out.println("Input is not a number!");
			return;
		}
		if ((number > 999999999999L) || (number < 0)) {
			System.out.println("Specified range doesn't support!");
			return;
		}

		NumberParser numberParser = new NumberParser();
		System.out.println(numberParser.parseNumber(input));
	}
}
