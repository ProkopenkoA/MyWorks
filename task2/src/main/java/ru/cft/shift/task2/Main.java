package ru.cft.shift.task2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static OutPut outPut;
    public static String outPutFileName;
    public static TypeFigure typeFigure;
    public static double[] segment = new double[3];
    public static UnitOfMeasure unitOfMeasure;

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("Запуск программы");
        ArgumentParser argParser = new ArgumentParser(args);
        Figure figure = null;
        PrintWriter pw = null;
        try {
            argParser.checkArgument();
            log.info("Проверил валидность аргументов");
            switch (typeFigure) {
                case CIRCLE -> figure = new Circle(segment[0]);
                case RECTANGLE -> figure = new Rectangle(segment[0], segment[1]);
                case TRIANGLE -> figure = new Triangle(segment[0], segment[1], segment[2]);
            }
            log.info("Провел рассчеты");
            switch (outPut) {
                case CONSOLE -> pw = new PrintWriter(System.out, true);
                case FILE -> pw = new PrintWriter(outPutFileName);
            }
            if (figure != null) {
                pw.printf(figure.forPrint(unitOfMeasure.getMeasure()));
            }
            log.info("Распечатал значение");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Ошибка тестового файла:", e);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ошибка: ", e);
        } finally {
            if (pw != null && outPut != OutPut.CONSOLE) {
                pw.close();
            }
            log.info("Завершил программу");
        }
    }
}
