package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTest {
    private final Rectangle rectangle = new Rectangle(4, 3);

    @Test
    void rectangle_rightLength() {
        Assertions.assertEquals(4, rectangle.length);
    }
    @Test
    void rectangle_rightWidth() {
        Assertions.assertEquals(3, rectangle.width);
    }
    @Test
    void rectangle_rightDiagonal() {
        Assertions.assertEquals(5, rectangle.diagonal);
    }
    @Test
    void rectangle_rightArea() {
        Assertions.assertEquals(12, rectangle.area);
    }
    @Test
    void rectangle_rightPerimeter() {
        Assertions.assertEquals(14, rectangle.perimeter);
    }
}
