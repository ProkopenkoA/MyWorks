package ru.cft.shift.task1;

import java.io.PrintWriter;

public class MultiplicationTables {
    private static final int maxDigitsOfNumber = 19;
    private static final char separator = '|';
    Integer size;
    Integer indent;//отступ, кол-во разрядов в числе
    Integer indentMaxIndex;

    public MultiplicationTables(Integer size) {
        this.size = size;
        this.indent = digitsOfNumber((long) size *size);
        this.indentMaxIndex = digitsOfNumber(size);
    }

    static int digitsOfNumber(long num) {
        long minNumberOfDigit = 10;
        for (int digit = 1; digit < maxDigitsOfNumber; digit++) {
            if (num < minNumberOfDigit)
                return digit;
            minNumberOfDigit = minNumberOfDigit * 10;
        }
        return maxDigitsOfNumber;
    }

    private String printLineTable(){
        StringBuilder lineTable;

        lineTable = new StringBuilder("\n");
        lineTable.append("-".repeat(Math.max(0, indentMaxIndex)));

        for(int index = size; index > 0; index--){
            lineTable.append("+");
            lineTable.append("-".repeat(Math.max(0, indent)));
        }
        return lineTable.toString();
    }

    private void printFirstNumber(long number, PrintWriter pw){
        for (int numIndent = digitsOfNumber(number); numIndent != indentMaxIndex; numIndent++){
            pw.print(" ");
        }
        pw.print(number);
    }

    private void printFirstLine(PrintWriter pw){
        for(int i = indentMaxIndex; i!=0; i--){
            pw.print(" ");
        }

        int numIndent;
        for(int index = 1; index <= size; index++){
            pw.print(separator);
            for(numIndent = indent - digitsOfNumber(index); numIndent != 0; numIndent --){
                pw.print(" ");
            }
            pw.print(index);
        }
    }

    public void printTable(PrintWriter pw){
        printFirstLine(pw);
        String lineTable = printLineTable();
        long number;
        int numIndent;
        pw.println(lineTable);

         for(long index1 = 1; index1 <= size; index1++){
             printFirstNumber(index1, pw);
             for(long index2 = 1; index2 <= size; index2++){
                 pw.print(separator);
                 number = index1*index2;
                 for(numIndent = indent - digitsOfNumber(number); numIndent != 0; numIndent --){
                     pw.print(" ");
                 }
                 pw.print(number);
             }
             pw.println(lineTable);
         }
    }
}
