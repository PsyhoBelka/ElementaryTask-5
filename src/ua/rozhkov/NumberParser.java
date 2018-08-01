package ua.rozhkov;


import java.util.HashMap;
import java.util.Map;

public class NumberParser {
	private int[] digits;
	private int[] digitRanks;
	private Map<Integer, String> dictionaryEd = new HashMap<>();
	private Map<Integer, String> dictionaryDec = new HashMap<>();
	private Map<Integer, String> dictionarySot = new HashMap<>();
	
	
	public void parseToDigits (String number) {
		//получаем число по цифрам
		int j = digits.length;
		for (int i = number.length() - 1; i >= 0; i--) {
			digits[j - 1] = Integer.parseInt(number.substring(i, i + 1));
			j--;
		}
	}
	
	public void parseToRanks (String number) {
		int j = 0;
		for (int i = 0; i < 3; i++) {
			digitRanks[i] = Integer.parseInt(String.valueOf(digits[j]) + String.valueOf(digits[j + 1]) + String.valueOf(digits[j + 2]));
			j += 3;
		}
	}
	
	public String parseRank (int number) {
		String result = "";
		if (number == 0) {
			return result;
		}
		
		initilize();
		
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
	
	private void initilize () {
//		Map<Integer, String> dictionaryEd = new HashMap<>();
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

//		Map<Integer, String> dictionaryDec = new HashMap<>();
		dictionaryDec.put(1, "десять");
		dictionaryDec.put(2, "двадцать");
		dictionaryDec.put(3, "тридцать");
		dictionaryDec.put(4, "сорок");
		dictionaryDec.put(5, "пятьдесят");
		dictionaryDec.put(6, "шестьдесят");
		dictionaryDec.put(7, "семьдесят");
		dictionaryDec.put(8, "восемьдесят");
		dictionaryDec.put(9, "девяносто");

//		Map<Integer, String> dictionarySot = new HashMap<>();
		dictionarySot.put(1, "сто");
		dictionarySot.put(2, "двести");
		dictionarySot.put(3, "триста");
		dictionarySot.put(4, "четыреста");
		dictionarySot.put(5, "пятьсот");
		dictionarySot.put(6, "шестьсот");
		dictionarySot.put(7, "семьсот");
		dictionarySot.put(8, "восемьсот");
		dictionarySot.put(9, "девятьсот");
	}
}
