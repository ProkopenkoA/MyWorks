package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Circle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    private final double radius;
    private final double diameter;

    public Circle(double radius) {
        this.radius = radius;
        this.diameter = radius + radius;
        calculateArea();
        calculatePerimeter();
        log.fine("Создан Circle");
    }

    @Override
    public void calculateArea() {
        super.area = radius * radius * Math.PI;
    }

    @Override
    public void calculatePerimeter() {
        super.perimeter = 2 * radius * Math.PI;
    }

    @Override
    public String forPrint(String unitOfMeasure) {
        return "Тип фигуры: Круг" + "\n" +
                super.forPrint(unitOfMeasure) +
                "Радиус: " + radius + " " + unitOfMeasure + "\n" +
                "Диаметр: " + diameter + " " + unitOfMeasure;
    }
}
