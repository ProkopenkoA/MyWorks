package ru.cft.shift.task4.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleReader.class);

    public long consoleReadNumber() {
        long number;
        String str;
        try(Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Введите число");
            while (true) {
                str = scanner.next();
                try {
                    number = Long.parseLong(str);
                    if (number > 0) {
                        break;
                    }
                    LOGGER.info("Нужно ввести число > 0");
                } catch (NumberFormatException e) {
                    LOGGER.info("Нужно ввести число");
                }
            }
        }
        return number;
    }
}
