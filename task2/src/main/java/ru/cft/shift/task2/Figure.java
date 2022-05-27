package ru.cft.shift.task2;

public abstract class Figure {
    public Figure() {
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    public abstract String getTypeFigure();

    public String forPrint(String unitOfMeasure) {
        return "Тип фигуры: " + getTypeFigure() + "\n" +
                "Площадь: " + calculateArea() + " " + unitOfMeasure + "^2" + "\n" +
                "Периметр: " + calculatePerimeter() + " " + unitOfMeasure + "\n";
    }
}
