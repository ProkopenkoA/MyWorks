package ru.cft.shift.task2;

import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Logger;

public class InputFileReader {
    double[] segment = new double[3];
    char type = 0;

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public InputFileReader(String fileName) throws Exception {
        try (FileReader fr = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fr);
            String argument;
            if (scanner.hasNext()) {
                argument = scanner.next();
                if (argument.equals("CIRCLE")) {
                    type = 'c';
                }
                if (argument.equals("RECTANGLE")) {
                    type = 'r';
                }
                if (argument.equals("TRIANGLE")) {
                    type = 't';
                }
                if (type == 0) {
                    throw new MyException("Ошибка в текстовом файле: первый аргумент - тип фигуры задан не верно");
                }
                if (type == 'c') {
                    if (scanner.hasNextDouble()) {
                        segment[0] = scanner.nextDouble();
                    } else {
                        throw new MyException("Ошибка в текстовом файле: второй аргумент - радиус круга, задан не верно");
                    }
                }
                if (type == 'r') {
                    for (int index = 0; index < 2; index++) {
                        if (scanner.hasNextDouble()) {
                            segment[index] = scanner.nextDouble();
                        } else {
                            throw new MyException("Ошибка в текстовом файле: аргумент - сторона квадрата, задан не верно");
                        }
                    }
                    if (segment[1] > segment[0]) {
                        segment[0] = segment[1] + segment[0];
                        segment[1] = segment[0] - segment[1];
                        segment[0] = segment[0] - segment[1];

                    }
                }
                if (type == 't') {
                    for (int index = 0; index < 3; index++) {
                        if (scanner.hasNextDouble()) {
                            segment[index] = scanner.nextDouble();
                        } else {
                            throw new MyException("Ошибка в текстовом файле: аргумент - сторона треугольника, задан не верно");
                        }
                    }
                    if (triangleRule(segment)) {
                        throw new MyException("Ошибка в текстовом файле: правило треугольника для задданных трех сторон не выполняется");
                    }
                }
            }
            if (type == 0) {
                throw new MyException("Ошибка: текстовый файл пуст");
            }
            if (scanner.hasNext()) {
                log.warning("В текстовом файле аргументов больше, чем требуется");

            }
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public double[] getSegment() {
        return segment;
    }

    public char getType() {
        return type;
    }

    private static boolean triangleRule(double[] side) {
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
}
