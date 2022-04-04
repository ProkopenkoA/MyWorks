package ru.cft.shift.task1;


import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out, true);
        CommandLineParser parser = new CommandLineParser();
        if (parser.validateArgs(args)) {
            MultiplicationTables table1 = new MultiplicationTables(parser.getSizeTable());
            table1.printTable(pw);
        }
    }
}
