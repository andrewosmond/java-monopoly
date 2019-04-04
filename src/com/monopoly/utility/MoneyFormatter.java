package com.monopoly.utility;

public class MoneyFormatter {
	public static String getFormat(long money) {
		long million = money/1000000;
		money = money % 1000000;
		long thousand = money/1000;
		money = money % 1000;
		long units = money;
		
		String res;
		if (million > 0) {
			if (thousand == 0) {
				res = String.format("%dM", (int)million);
			} else {
				res = String.format("%dM%d", (int)million, (int)thousand);
			}
		} else if (thousand > 0) {
			if (units == 0) {
				res = String.format("%dK", (int)thousand);
			} else {
				res = String.format("%dK%d", (int)thousand, (int)units);
			}
		} else {
			res = String.format("%d", (int)units);
		}
		return res;
	}
}
