package ru.cft.shift.task2;

public enum ConsoleArguments {
    CONSOLE("c"),
    INPUTFILE("i"),
    OUTPUTFILE("o");

    private final String symbol;

    ConsoleArguments(String symbol) {
        this.symbol = symbol;
    }

    public static ConsoleArguments findByText(String param) {
        for (ConsoleArguments arg : ConsoleArguments.values()) {
            if (arg.symbol.equals(param)) {
                return arg;
            }
        }
        return null;
    }
}
