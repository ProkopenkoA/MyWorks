package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Rectangle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    private final double diagonal;
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {

        this.length = Math.max(length, width);
        this.width = Math.min(length, width);
        this.diagonal = Math.sqrt(length * length + width * width);
        calculateArea();
        calculatePerimeter();
        log.fine("Создан Rectangle");
    }

    @Override
    public void calculateArea() {
        super.area = length * width;
    }

    @Override
    public void calculatePerimeter() {
        super.perimeter = length + length + width + width;
    }

    @Override
    public String forPrint(String unitOfMeasure) {
        return "Тип фигуры: Прямоугольник" + "\n" +
                super.forPrint(unitOfMeasure) +
                "Длина: " + length + " " + unitOfMeasure + "\n" +
                "Ширина: " + width + " " + unitOfMeasure + "\n" +
                "Диагональ: " + diagonal + " " + unitOfMeasure;
    }
}
