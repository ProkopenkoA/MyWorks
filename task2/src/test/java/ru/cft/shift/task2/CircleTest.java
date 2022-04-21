package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircleTest {
    private final Circle circle = new Circle(1);

    @Test
    void circle_rightArea() {
        Assertions.assertEquals(Math.PI, circle.calculateArea(), 0.001);
    }

    @Test
    void circle_rightPerimeter() {
        Assertions.assertEquals(2 * Math.PI, circle.calculatePerimeter(), 0.001);
    }

    @Test
    void circle_rightForPrint() {
        String str = """
                Тип фигуры: Круг
                Площадь: 3.141592653589793 м^2
                Периметр: 6.283185307179586 м
                Радиус: 1.0 м
                Диаметр: 2.0 м""";
        Assertions.assertEquals(str, circle.forPrint("м"));
    }
}
