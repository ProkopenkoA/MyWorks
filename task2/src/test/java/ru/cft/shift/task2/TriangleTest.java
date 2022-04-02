package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    private Triangle triangle = new Triangle(5, 12, 13);

    @Test
    void triangle_rightSides() {
        Assertions.assertEquals(5, triangle.side1);
        Assertions.assertEquals(12, triangle.side2);
        Assertions.assertEquals(13, triangle.side3);
    }

    @Test
    void triangle_rightPerimeter() {
        Assertions.assertEquals(30, triangle.perimeter);
    }
    @Test
    void triangle_rightArea() {
        Assertions.assertEquals(30, triangle.area);
    }
    @Test
    void triangle_rightAngle() {
        Assertions.assertEquals(90, triangle.angle3);
    }

    @Test
    void triangle_rightAngles() {
        triangle = new Triangle(1,1,1);
        for (double v : new double[]{triangle.angle1, triangle.angle2, triangle.angle3}) {
            Assertions.assertEquals(60, Math.round(v));
        }
    }
}
