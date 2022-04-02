package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Rectangle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    double diagonal;
    double length;
    double width;

    public Rectangle(double length, double width) {

        log.fine("Создан Rectangle");

        this.length = length;
        this.width = width;
        this.diagonal = Math.sqrt(length * length + width * width);
        super.area = length * width;
        super.perimeter = length + length + width + width;
    }

    public String forPrint() {
        return "Тип фигуры: Прямоугольник" + "\n" +
                "Площадь: " + area + "\n" +
                "Периметр: " + perimeter + "\n" +
                "Длина: " + length + "\n" +
                "Ширина: " + width + "\n" +
                "Диагональ: " + diagonal;
    }
}
