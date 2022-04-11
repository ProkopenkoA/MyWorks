package ru.cft.shift.task1;

public class CommandLineParser {
    private static final char ARGUMENT_PREFIX = '-';
    private static final String ARGUMENT_NAME_SIZE_TABLE = "sizeTable";
    int sizeTable;


    public CommandLineParser() {
    }

    public int getSizeTable() {
        return sizeTable;
    }

    public boolean validateArgs(String[] args) {
        if (args.length != 2) {
            System.out.println("Ошибка ввода входных данных: задано неверное кол-во параметров");
            return false;
        }
        if (args[0].charAt(0) == ARGUMENT_PREFIX) {
            args[0] = args[0].substring(1);
        } else {
            System.out.println("Ошибка ввода входных данных: параметр начинается не с '-'");
            return false;
        }
        if (args[0].equals(ARGUMENT_NAME_SIZE_TABLE)) {
            try {
                this.sizeTable = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка ввода входных данных: неверный тип числа");
                return false;
            }
            if (sizeTable < 1 || sizeTable > 32) {
                System.out.println("Ошибка ввода входных данных: число задано не верно");
                return false;
            }
        } else {
            System.out.println("Ошибка ввода входных данных: имя параметра введено не верно");
            return false;
        }
        return true;
    }
}
