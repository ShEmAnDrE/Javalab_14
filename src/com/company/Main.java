package com.company;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final Pattern DATE_PATTERN = Pattern.compile("^([0-9]{2})\\/([0-9]{2})\\/([0-9]{4})$");
    private static final int[] quantityOfDaysInMonths = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean checkYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean checkDate(int day, int month, int year) {
        if (year < 1900 || year > 9999) {
            return false;
        }
        if (month > 12) {
            return false;
        }
        if (month == 2) {
            if (checkYear(year)) {
                if (day > 29) {
                    return false;
                }
            } else {
                if (day > 28) {
                    return false;
                }
            }
        } else {
            if (day > quantityOfDaysInMonths[month - 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkString(String dateAsString) {
        Matcher m = DATE_PATTERN.matcher(dateAsString);
        boolean result = m.matches();
        if (result) {
            int day = Integer.parseInt(m.group(1));
            int month = Integer.parseInt(m.group(2));
            int year = Integer.parseInt(m.group(3));
            return checkDate(day, month, year);
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "";
        String date = "";
        boolean f = true;
        while (f) {
            System.out.println("Выберите одну из команд:");
            System.out.println("1 - Проверить дату");
            System.out.println("Любая другая клавиша - Выход из программы");
            choice = sc.nextLine();
            switch (choice.charAt(0)) {
                case '1':
                    System.out.println("Введите дату");
                    date = sc.nextLine();
                    if (checkString(date)) {
                        System.out.println("Дата корректна");
                    } else {
                        System.out.println("Дата некорректна");
                    }
                    break;
                default:
                    System.out.println("До свидания!");
                    f = false;
            }
        }
    }
}
