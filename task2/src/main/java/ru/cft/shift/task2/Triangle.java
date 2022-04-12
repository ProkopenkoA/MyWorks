package ru.cft.shift.task2;

import java.util.logging.Logger;

public class Triangle extends Figure {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    private final double side1;
    private final double side2;
    private final double side3;
    private final double angle1;
    private final double angle2;
    private final double angle3;

    public Triangle(double side1, double side2, double side3) {

        log.fine("Создан Triangle");
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.angle1 = angleTriangle(side1, side2, side3);
        this.angle2 = angleTriangle(side2, side1, side3);
        this.angle3 = 180 - angle1 - angle2;
        calculatePerimeter();
        calculateArea();
    }

    private double angleTriangle(final double oppositeLeg, final double adjacentLeg1, final double adjacentLeg2) {
        return Math.toDegrees(Math.acos((adjacentLeg1 * adjacentLeg1 + adjacentLeg2 * adjacentLeg2 - oppositeLeg * oppositeLeg) / 2 / adjacentLeg2 / adjacentLeg1));
    }

    @Override
    public void calculateArea() {
        double halfPerimeter = perimeter / 2;
        super.area = Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));
    }

    @Override
    public void calculatePerimeter() {
        super.perimeter = side1 + side2 + side3;
    }

    @Override
    public String forPrint(String unitOfMeasure) {
        return "Тип фигуры: Треугольник" + "\n" +
                super.forPrint(unitOfMeasure) +
                "Сторона 1: " + side1 + " " + unitOfMeasure + "\n" +
                "Угол 1: " + angle1 + "°" + "\n" +
                "Сторона 2: " + side2 + " " + unitOfMeasure + "\n" +
                "Угол 2: " + angle2 + "°" + "\n" +
                "Сторона 3: " + side3 + " " + unitOfMeasure + "\n" +
                "Угол 3: " + angle3 + "°";
    }
}
