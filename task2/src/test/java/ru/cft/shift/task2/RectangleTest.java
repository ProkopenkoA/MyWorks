package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTest {
    private final Rectangle rectangle = new Rectangle(4, 3);

    @Test
    void rectangle_rightArea() {
        Assertions.assertEquals(12, rectangle.area, 0.001);
    }

    @Test
    void rectangle_rightPerimeter() {
        Assertions.assertEquals(14, rectangle.perimeter, 0.001);
    }

    @Test
    void rectangle_rightForPrint() {
        String str = """
                Тип фигуры: Прямоугольник
                Площадь: 12.0 м^2
                Периметр: 14.0 м
                Длина: 4.0 м
                Ширина: 3.0 м
                Диагональ: 5.0 м""";
        Assertions.assertEquals(str, rectangle.forPrint("м"));
    }
}
