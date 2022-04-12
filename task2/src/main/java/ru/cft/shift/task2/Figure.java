package ru.cft.shift.task2;

public abstract class Figure {
    protected double area;
    protected double perimeter;

    public Figure() {
    }

    public abstract void calculateArea();

    public abstract void calculatePerimeter();

    public String forPrint(String unitOfMeasure) {
        return "Площадь: " + area + " " + unitOfMeasure + "^2" + "\n" +
                "Периметр: " + perimeter + " " + unitOfMeasure + "\n";
    }
}
