package ru.cft.shift.task2;

import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Logger;

import static ru.cft.shift.task2.Main.typeFigure;
import static ru.cft.shift.task2.Main.segment;
import static ru.cft.shift.task2.Main.unitOfMeasure;

public class InputFileReader {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    private final String fileName;


    public InputFileReader(String fileName) {
        this.fileName = fileName;
    }

    public void checkFileInput() throws Exception {
        try (FileReader fr = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fr);
            if (scanner.hasNext()) {
                typeFigure = TypeFigure.valueOf(scanner.next());
                switch (typeFigure) {
                    case CIRCLE -> nextSegment(scanner, 0);
                    case RECTANGLE -> {
                        for (int index = 0; index < 4; index = index + 2) {
                            nextSegment(scanner, index);
                        }
                    }
                    case TRIANGLE -> {
                        for (int index = 0; index < 6; index = index + 2) {
                            nextSegment(scanner, index);
                        }
                        if (hasNotTriangleRule(segment)) {
                            throw new MyException("Ошибка в текстовом файле: правило треугольника для задданных трех сторон не выполняется");
                        }
                    }
                }
            }
            if (typeFigure == null) {
                throw new MyException("Ошибка: текстовый файл пуст");
            }
            if (scanner.hasNext()) {
                log.warning("В текстовом файле аргументов больше, чем требуется");
            }
        }
    }

    private static boolean hasNotTriangleRule(double[] side) {
        if (side[0] + side[1] <= side[2]) {
            return true;
        }
        if (side[0] + side[2] <= side[1]) {
            return true;
        }
        if (side[1] + side[2] <= side[0]) {
            return true;
        }
        return false;
    }

    private void nextSegment(Scanner scanner, int index) throws MyException {
        if (scanner.hasNextDouble()) {
            segment[index / 2] = scanner.nextDouble();
            if (segment[index / 2] > 0) {
                nextUnitMeasure(scanner);
                return;
            }
        }
        throw new MyException("Ошибка в текстовом файле: аргумент задан не верно");
    }

    private void nextUnitMeasure(Scanner scanner) throws MyException {
        String text;
        if (scanner.hasNext()) {
            text = scanner.next();
            if (unitOfMeasure == null) {
                unitOfMeasure = UnitOfMeasure.findByText(text);
                if (unitOfMeasure == null) {
                    throw new MyException("Ошибка ввода единиц измерения: " + text + " - нет совпадений");
                }
            }
            if (unitOfMeasure != UnitOfMeasure.findByText(text)) {
                throw new MyException("Ошибка ввода единиц измерения");
            }
        }
    }
}
