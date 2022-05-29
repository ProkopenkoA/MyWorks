package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Rectangle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    private static final String TYPE_FIGURE = "Прямоугольник";

    private final double diagonal;
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {

        this.length = Math.max(length, width);
        this.width = Math.min(length, width);
        this.diagonal = Math.sqrt(length * length + width * width);
        log.fine("Создан Rectangle");
    }

    @Override
    public String getTypeFigure() {
        return TYPE_FIGURE;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return length + length + width + width;
    }

    @Override
    public String forPrint(String unitOfMeasure) {
        return super.forPrint(unitOfMeasure) +
                "Длина: " + length + " " + unitOfMeasure + "\n" +
                "Ширина: " + width + " " + unitOfMeasure + "\n" +
                "Диагональ: " + diagonal + " " + unitOfMeasure;
    }
}
