package ua.rozhkov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberParser {

	//	private int[] digits;
//	private int[] digitRanks;
	private Map<Integer, String> dictionaryEd = new HashMap<>();
	private Map<Integer, String> dictionaryDec = new HashMap<>();
	private Map<Integer, String> dictionarySot = new HashMap<>();

	public NumberParser() {
		initializeDictionary();
	}

	//парсинг числа в строкове представление
	public String parseNumber(String number) {
		String propis = "";
		int[] digits = parseToDigits(number);
		int[] digitRanks = parseToRanks(digits);

		//парсинг по разрядам
		//парсинг миллиардов
		if (digitRanks[0] != 0) {
			propis += parseRank(digitRanks[0]);
			if (digits[2] == 0) {                                                //0
				propis += " миллиардов ";
			} else {
				if (digits[2] == 1) {                                            //1
					propis += " миллиард ";
				} else {
					if (digits[2] > 1 && digits[2] <= 4) {            //2-4
						propis += " миллиарда ";
					} else {                                                            //5-9
						propis += " миллиардов ";
					}
				}
			}
		}

		//парсинг миллионов
		if (digitRanks[1] != 0) {
			propis += parseRank(digitRanks[1]);
			if (digits[5] == 0) {
				propis += " миллионов ";
			} else {
				if (digits[5] == 1) {
					propis += " миллион ";
				} else {
					if (digits[5] > 1 && digits[5] <= 4) {
						propis += " миллиона ";
					} else {
						propis += " миллионов ";
					}
				}
			}
		}

		//парсинг тысяч
		if (digitRanks[2] != 0) {
			propis += parseRank(digitRanks[2]);
			if (digits[8] == 0) {
				propis += " тысяч ";
			} else {
				if (digits[8] == 1) {
					propis += " тысяча ";
				} else {
					if (digits[8] > 1 && digits[8] <= 4) {
						propis += " тысячи";
					} else {
						propis += " тысяч ";
					}
				}
			}
		}

		//парсинг сотен
		if (digitRanks[3] != 0) {
			propis += Main.parseRank(digitRanks[3]);
		} else {
			propis = dictionaryEd.get(0);
		}

		return propis;
	}

	//получаем число по цифрам
	private int[] parseToDigits(String number) {
		int[] result = new int[12];
		int j = result.length;
		for (int i = number.length() - 1; i >= 0; i--) {
			result[j - 1] = Integer.parseInt(number.substring(i, i + 1));
			j--;
		}

		return result;
	}

	//получаем число по разрядам
	private int[] parseToRanks(int[] digits) {
		int j = 0;
		int[] result = new int[4];
		for (int i = 0; i < 4; i++) {
			result[i] = Integer.parseInt(String.valueOf(digits[j]) + String.valueOf(digits[j + 1]) + String.valueOf(digits[j + 2]));
			j += 3;
		}

		return result;
	}

	//получаем символьное представление разряда
	private String parseRank(int number) {
		String result = "";
		if (number == 0) {
			return result;
		}
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

	private void initializeDictionary() {
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
