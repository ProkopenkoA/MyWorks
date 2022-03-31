package ru.cft.shift.task1;


import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try {
            TryArguments tryArgs = new TryArguments(args);
            MultiplicationTables table1 = new MultiplicationTables(tryArgs.getSizeTable());
            PrintWriter pw = new PrintWriter(System.out, true);
            table1.printTable(pw);
        } catch (NumberFormatException e){
            System.out.println("Ошибка ввода входных данных: заданный аргумент не нужного формата");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
