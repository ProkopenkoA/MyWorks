package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Circle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    private static final String TYPE_FIGURE = "Круг";

    private final double radius;
    private final double diameter;

    public Circle(double radius) {
        this.radius = radius;
        this.diameter = radius + radius;
        log.fine("Создан Circle");
    }

    @Override
    public String getTypeFigure() {
        return TYPE_FIGURE;
    }

    @Override
    public double calculateArea() {
        return radius * radius * Math.PI;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String forPrint(String unitOfMeasure) {
        return super.forPrint(unitOfMeasure) +
                "Радиус: " + radius + " " + unitOfMeasure + "\n" +
                "Диаметр: " + diameter + " " + unitOfMeasure;
    }
}
