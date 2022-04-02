package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Circle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    double radius;
    double diameter;

    public Circle(double radius) {
        log.fine("Создан Circle");
        this.radius = radius;
        this.diameter = radius + radius;
        super.area = radius * radius * Math.PI;
        super.perimeter = 2 * radius * Math.PI;
    }

    public String forPrint() {
        return "Тип фигуры: Круг" + "\n" +
                "Площадь: " + area + "\n" +
                "Периметр: " + perimeter + "\n" +
                "Радиус: " + radius + "\n" +
                "Диаметр: " + diameter;
    }
}
