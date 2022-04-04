package ru.cft.shift.task1;

import java.io.PrintWriter;

public class MultiplicationTables {
    private static final int MAX_DIGITS_OF_NUMBER = 19;
    private static final char SEPARATOR_VERTICAL = '|';
    private static final char SEPARATOR_OF_HORIZONTAL = '+';
    private static final String SEPARATOR_HORIZONTAL = "-";
    int size;
    int indent;
    int indentMaxIndex;

    public MultiplicationTables(int size) {
        this.size = size;
        this.indent = digitsOfNumber((long) size * size);
        this.indentMaxIndex = digitsOfNumber(size);
    }

    static int digitsOfNumber(long num) {
        long minNumberOfDigit = 10;
        for (int digit = 1; digit < MAX_DIGITS_OF_NUMBER; digit++) {
            if (num < minNumberOfDigit)
                return digit;
            minNumberOfDigit = minNumberOfDigit * 10;
        }
        return MAX_DIGITS_OF_NUMBER;
    }

    private String getHorizontalLine() {
        StringBuilder lineTable = new StringBuilder((size + 1) * (indent + 1));
        lineTable.append("\n");
        lineTable.append(SEPARATOR_HORIZONTAL.repeat(indentMaxIndex));

        for (int index = size; index > 0; index--) {
            lineTable.append(SEPARATOR_OF_HORIZONTAL);
            lineTable.append(SEPARATOR_HORIZONTAL.repeat(indent));
        }
        return lineTable.toString();
    }

    private void printFirstNumber(long number, PrintWriter pw) {
        for (int numIndent = digitsOfNumber(number); numIndent != indentMaxIndex; numIndent++) {
            pw.print(" ");
        }
        pw.print(number);
    }

    private void printFirstLine(PrintWriter pw) {
        for (int i = indentMaxIndex; i != 0; i--) {
            pw.print(" ");
        }

        int numIndent;
        for (int index = 1; index <= size; index++) {
            pw.print(SEPARATOR_VERTICAL);
            for (numIndent = indent - digitsOfNumber(index); numIndent != 0; numIndent--) {
                pw.print(" ");
            }
            pw.print(index);
        }
    }

    public void printTable(PrintWriter pw) {
        printFirstLine(pw);
        String lineTable = getHorizontalLine();
        long number;
        int numIndent;
        pw.println(lineTable);

        for (long rowIndex = 1; rowIndex <= size; rowIndex++) {
            printFirstNumber(rowIndex, pw);
            for (long cellIndex = 1; cellIndex <= size; cellIndex++) {
                pw.print(SEPARATOR_VERTICAL);
                number = rowIndex * cellIndex;
                for (numIndent = indent - digitsOfNumber(number); numIndent != 0; numIndent--) {
                    pw.print(" ");
                }
                pw.print(number);
            }
            pw.println(lineTable);
        }
    }
}
