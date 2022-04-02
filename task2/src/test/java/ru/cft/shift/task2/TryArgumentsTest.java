package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TryArgumentsTest {
    TryArguments tryArgs;
    @Test
    public void TryArguments_ExceptionCountOfArgs() {
        String[] args = new String[]{"-c"};
        Exception exception = Assertions.assertThrows(MyException.class, () -> tryArgs = new TryArguments(args));

        assertTrue(exception.getMessage().contains("Ошибка входных данных: кол-во параметров задано не верно"));
    }

    @Test
    public void TryArguments_ExceptionNoTilda() {
        String[] args = new String[]{"-c", "Input.txt"};
        Exception exception = Assertions.assertThrows(MyException.class, () -> tryArgs = new TryArguments(args));

        assertTrue(exception.getMessage().contains("Ошибка ввода входных данных: параметр начинается не с '-'"));
    }
}
