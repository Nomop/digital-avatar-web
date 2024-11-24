package com.web.converter;

public class NumberToChineseConverter {
    public static String convert(int num) {
        String[] chineseNumbers = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        StringBuilder result = new StringBuilder();

        if (num == 0) {
            return chineseNumbers[0];
        }

        while (num > 0) {
            int digit = num % 10;
            result.insert(0, chineseNumbers[digit]);
            num /= 10;
        }

        return result.toString();
    }
}

