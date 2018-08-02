package ua.rozhkov;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		
		System.out.print("Input number (max 999 999 999): ");
		String input = new Scanner(System.in).nextLine();
		int[] digits = new int[11];
		long number = Long.parseUnsignedLong(input);
		if ((number > 999999999999L) || (number < 0)) {
			System.out.println("Specified range doesn't support!");
			return;
		}

		NumberParser numberParser = new NumberParser();
		System.out.println(numberParser.parseNumber(input));
		
		/*//получаем число по цифрам
		int j = digits.length;
		for (int i = input.length() - 1; i >= 0; i--) {
			digits[j - 1] = Integer.parseInt(input.substring(i, i + 1));
			j--;
		}
		
		//получаем число поразрядно
		int[] digitRanks = new int[3];
		j = 0;
		for (int i = 0; i < 3; i++) {
			digitRanks[i] = Integer.parseInt(String.valueOf(digits[j]) + String.valueOf(digits[j + 1]) + String.valueOf(digits[j + 2]));
			j += 3;
		}
		
		String propis = "";
		//парсинг миллионов
		if (digitRanks[0] != 0) {
			propis += Main.parseRank(digitRanks[0]);
			if (digits[2] == 0) {
				propis += " миллионов ";
			} else {
				if (digits[2] == 1) {
					propis += " миллион ";
				} else {
					if (digits[2] > 1 && digits[2] <= 4) {
						propis += " миллиона ";
					} else {
						propis += " миллионов ";
					}
				}
			}
		}
		
		//парсинг тысяч
		if (digitRanks[1] != 0) {
			propis += Main.parseRank(digitRanks[1]);
			if (digits[2] == 0) {
				propis += " тысяч ";
			} else {
				if (digits[2] == 1) {
					propis += " тысяча ";
				} else {
					if (digits[2] > 1 && digits[2] <= 4) {
						propis += " тысячи";
					} else {
						propis += " тысяч ";
					}
				}
			}
		}
		
		//парсинг сотен
		if (digitRanks[2] != 0) {
			propis += Main.parseRank(digitRanks[2]);
		} else {
			System.out.println("ноль");
		}*/

//		System.out.println(propis);
	}
	
	public static String parseRank (int number) {
		
		String result = "";
		if (number == 0) {
			return result;
		}
		Map<Integer, String> dictionaryEd = new HashMap<>();
		dictionaryEd.put(0, "ноль");
		dictionaryEd.put(1, "один");
		dictionaryEd.put(2, "два");
		dictionaryEd.put(3, "три");
		dictionaryEd.put(4, "четыре");
		dictionaryEd.put(5, "пять");
		dictionaryEd.put(6, "шесть");
		dictionaryEd.put(7, "семь");
		dictionaryEd.put(8, "восемь");
		dictionaryEd.put(9, "девять");
		
		dictionaryEd.put(10, "десять");
		dictionaryEd.put(11, "одиннадцать");
		dictionaryEd.put(12, "двенадцать");
		dictionaryEd.put(13, "тринадцать");
		dictionaryEd.put(14, "четырнадцать");
		dictionaryEd.put(15, "пятнадцать");
		dictionaryEd.put(16, "шестьнадцать");
		dictionaryEd.put(17, "семьнадцать");
		dictionaryEd.put(18, "восемьнадцать");
		dictionaryEd.put(19, "девятнадцать");
		
		Map<Integer, String> dictionaryDec = new HashMap<>();
		dictionaryDec.put(1, "десять");
		dictionaryDec.put(2, "двадцать");
		dictionaryDec.put(3, "тридцать");
		dictionaryDec.put(4, "сорок");
		dictionaryDec.put(5, "пятьдесят");
		dictionaryDec.put(6, "шестьдесят");
		dictionaryDec.put(7, "семьдесят");
		dictionaryDec.put(8, "восемьдесят");
		dictionaryDec.put(9, "девяносто");
		
		Map<Integer, String> dictionarySot = new HashMap<>();
		dictionarySot.put(1, "сто");
		dictionarySot.put(2, "двести");
		dictionarySot.put(3, "триста");
		dictionarySot.put(4, "четыреста");
		dictionarySot.put(5, "пятьсот");
		dictionarySot.put(6, "шестьсот");
		dictionarySot.put(7, "семьсот");
		dictionarySot.put(8, "восемьсот");
		dictionarySot.put(9, "девятьсот");
		
		if (number / 100 != 0) {
			result += dictionarySot.get(number / 100) + " ";
		}
		if (number % 100 > 19) {
			int numberDec = number % 100;
			result += dictionaryDec.get(numberDec / 10) + " " + dictionaryEd.get(numberDec % 10);
		} else {
			result += dictionaryEd.get(number % 100);
		}
		return result;
	}
}
