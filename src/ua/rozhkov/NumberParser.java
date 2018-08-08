package ua.rozhkov;

import java.util.HashMap;
import java.util.Map;

public class NumberParser {
	
	private static final byte MAX_DIGIT = 12;
	private static final byte MAX_RANK = 4;
	private Map<Integer, String> dictionaryEd = new HashMap<>();
	private Map<Integer, String> dictionaryDec = new HashMap<>();
	private Map<Integer, String> dictionarySot = new HashMap<>();
	private Map<Integer, String> dictionaryHun = new HashMap<>();
	
	
	//парсинг числа в строкове представление
	public String parseNumber (String number) {
		String propis = "";
		long numberIn = Long.parseLong(number);
		
		if (numberIn == 0) {
			return "ноль";
		}
		
		if (numberIn <= 0) {
			propis += "минус ";
			numberIn *= -1;
		}
		
		int[] digits = parseToDigits(String.valueOf(numberIn));
		int[] digitRanks = parseToRanks(digits);
		initializeDictionary();
		
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
			if (digitRanks[2] == 1) {
				propis += "одна тысяча ";
			} else {
				if (digitRanks[2] == 2) {
					propis += "две тысячи ";
				} else {
					if (dictionaryHun.containsKey(digitRanks[2])) {
						propis += dictionaryHun.get(digitRanks[2]) + " тысяча ";
					} else {
						propis += parseRank(digitRanks[2]);
						
						if (digits[8] == 0) {
							propis += " тысяч ";
						} else {
							if (digits[7] >= 2) {
								if (digits[8] > 1 && digits[8] <= 4) {
									propis += " тысячи ";
								} else {
									propis += " тысяч ";
								}
							} else {
								if (digits[8] > 1 && digits[8] <= 4) {
									propis += " тысячи ";
								} else {
									propis += " тысяч ";
								}
							}
						}
					}
				}
			}
		}
		
		//парсинг сотен
		if (digitRanks[3] != 0) {
			propis += parseRank(digitRanks[3]);
		}
		
		return propis.trim();
	}
	
	//получаем число по цифрам
	private int[] parseToDigits (String number) {
		int[] result = new int[MAX_DIGIT];
		int j = result.length;
		for (int i = number.length() - 1; i >= 0; i--) {
			result[j - 1] = Integer.parseInt(number.substring(i, i + 1));
			j--;
		}
		
		return result;
	}
	
	//получаем число по разрядам
	private int[] parseToRanks (int[] digits) {
		int j = 0;
		int[] result = new int[MAX_RANK];
		for (int i = 0; i < MAX_RANK; i++) {
			result[i] = Integer.parseInt(String.valueOf(digits[j]) + String.valueOf(digits[j + 1]) + String.valueOf(digits[j + 2]));
			j += 3;
		}
		
		return result;
	}
	
	//получаем символьное представление разряда
	private String parseRank (int number) {
		String result = "";
		int number_100 = number / 100;                    //получаем сотни
		int number_10 = (number % 100) / 10;          //получаем десятки
		int number_1 = ((number % 100) % 10);        //получаем единицы
		
		if (number == 0) {
			return result;
		}
		if (number_100 != 0) {
			result += dictionarySot.get(number_100);
		}
		if (number % 100 <= 19) {
			result += " " + dictionaryEd.get(number % 100);
		} else {
			if (number_10 != 0) {
				result += " " + dictionaryDec.get(number_10);
			}
			if (number_1 != 0) {
				result += " " + dictionaryEd.get(number_1);
			}
		}
		
		return result.trim();
	}
	
	private void initializeDictionary () {
		//словарь 1-9
		dictionaryEd.put(0, "");
		dictionaryEd.put(1, "один");
		dictionaryEd.put(2, "два");
		dictionaryEd.put(3, "три");
		dictionaryEd.put(4, "четыре");
		dictionaryEd.put(5, "пять");
		dictionaryEd.put(6, "шесть");
		dictionaryEd.put(7, "семь");
		dictionaryEd.put(8, "восемь");
		dictionaryEd.put(9, "девять");
		
		//словарь 10-19
		dictionaryEd.put(10, "десять");
		dictionaryEd.put(11, "одиннадцать");
		dictionaryEd.put(12, "двенадцать");
		dictionaryEd.put(13, "тринадцать");
		dictionaryEd.put(14, "четырнадцать");
		dictionaryEd.put(15, "пятнадцать");
		dictionaryEd.put(16, "шестнадцать");
		dictionaryEd.put(17, "семнадцать");
		dictionaryEd.put(18, "восемнадцать");
		dictionaryEd.put(19, "девятнадцать");
		
		//словарь десятков 10-90
		dictionaryDec.put(1, "десять");
		dictionaryDec.put(2, "двадцать");
		dictionaryDec.put(3, "тридцать");
		dictionaryDec.put(4, "сорок");
		dictionaryDec.put(5, "пятьдесят");
		dictionaryDec.put(6, "шестьдесят");
		dictionaryDec.put(7, "семьдесят");
		dictionaryDec.put(8, "восемьдесят");
		dictionaryDec.put(9, "девяносто");
		
		//словарь сотен 100-900
		dictionarySot.put(1, "сто");
		dictionarySot.put(2, "двести");
		dictionarySot.put(3, "триста");
		dictionarySot.put(4, "четыреста");
		dictionarySot.put(5, "пятьсот");
		dictionarySot.put(6, "шестьсот");
		dictionarySot.put(7, "семьсот");
		dictionarySot.put(8, "восемьсот");
		dictionarySot.put(9, "девятьсот");
		
		//словарь тысяч
		dictionaryHun.put(21, "двадцать одна");
		dictionaryHun.put(31, "тридцать одна");
		dictionaryHun.put(41, "сорок одна");
		dictionaryHun.put(51, "пятьдесят одна");
		dictionaryHun.put(61, "шестьдесят одна");
		dictionaryHun.put(71, "семьдесят одна");
		dictionaryHun.put(81, "восемьдесят одна");
		dictionaryHun.put(91, "девяносто одна");
		dictionaryHun.put(101, "сто одна");
		
	}
}
