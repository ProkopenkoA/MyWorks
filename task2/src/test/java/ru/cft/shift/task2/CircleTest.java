package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircleTest {
    private final Circle circle = new Circle(1);

    @Test
    void circle_rightRadius() {
        Assertions.assertEquals(1, circle.radius);
    }

    @Test
    void circle_rightDiameter() {
        Assertions.assertEquals(2, circle.diameter);
    }

    @Test
    void circle_rightArea() { Assertions.assertEquals(Math.PI, circle.area); }

    @Test
    void circle_rightPerimeter() {
        Assertions.assertEquals(2 * Math.PI, circle.perimeter);
    }
}
