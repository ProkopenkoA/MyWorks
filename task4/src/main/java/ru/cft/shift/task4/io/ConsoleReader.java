package ru.cft.shift.task4.io;

import java.util.Scanner;

public class ConsoleReader {

    public long consoleReadNumber() {
        long number;
        String str;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        while (true) {
            str = scanner.next();
            try {
                number = Long.parseLong(str);
                if (number > 0) {
                    break;
                }
                System.out.println("Нужно ввести число > 0");
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести число");
            }
        }
        scanner.close();
        return number;
    }
}
