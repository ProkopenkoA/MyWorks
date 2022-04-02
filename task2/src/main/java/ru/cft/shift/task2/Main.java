package ru.cft.shift.task2;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("Запуск программы");
        PrintWriter pw = new PrintWriter(System.out);
        Figure figure = null;
        try {
            TryArguments tryArgs = new TryArguments(args);
            log.info("Проверил валидность аргументов");

            switch (tryArgs.getType()) {
                case 'c' -> figure = new Circle(tryArgs.getSegment()[0]);
                case 'r' -> figure = new Rectangle(tryArgs.getSegment()[0], tryArgs.getSegment()[1]);
                case 't' -> figure = new Triangle(tryArgs.getSegment()[0], tryArgs.getSegment()[1], tryArgs.getSegment()[2]);
            }
            log.info("Провел рассчеты");
            switch (tryArgs.getOutput()) {
                case 'c' -> pw = new PrintWriter(System.out, true);
                case 'f' -> pw = new PrintWriter(tryArgs.getOutPutFileName());
            }
            if (figure != null) {
                pw.print(figure.forPrint());
            }
            log.info("Распечатал значение");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception: ", e);
        } finally {
            pw.close();
            log.info("Завершил программу");
        }
    }
}
