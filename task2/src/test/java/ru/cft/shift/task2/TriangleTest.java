package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    private final Triangle triangle = new Triangle(5, 12, 13);

    @Test
    void triangle_rightPerimeter() {
        Assertions.assertEquals(30, triangle.calculatePerimeter(), 0.001);
    }

    @Test
    void triangle_rightArea() {
        Assertions.assertEquals(30, triangle.calculateArea(), 0.001);
    }

    @Test
    void triangle_rightForPrint() {
        String str = """
                Тип фигуры: Треугольник
                Площадь: 30.0 м^2
                Периметр: 30.0 м
                Сторона 1: 5.0 м
                Угол 1: 22.619864948040433°
                Сторона 2: 12.0 м
                Угол 2: 67.38013505195957°
                Сторона 3: 13.0 м
                Угол 3: 90.0°""";
        Assertions.assertEquals(str, triangle.forPrint("м"));
    }
}
