package ru.cft.shift.task2;

import java.io.*;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputFileReaderTest {
    private static File tmp;

    @BeforeAll
    public static void beforeAll() throws IOException {
        tmp = Files.createTempFile("inputFile", null).toFile();
    }

    @Test
    void InputFileReader_ExceptionNotRightType() {
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("Circle" + "\n" +
                        "5");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
        });
        assertTrue(exception.getMessage().contains("Ошибка в текстовом файле: первый аргумент - тип фигуры задан не верно"));
    }

    @Test
    void InputFileReader_ExceptionNotRightSegment() {
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("RECTANGLE" + "\n" +
                        "5 one");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
        });
        assertTrue(exception.getMessage().contains("Ошибка в текстовом файле: аргумент - сторона квадрата, задан не верно"));
    }
    @Test
    void InputFileReader_ExceptionRuleTriangle() {
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("TRIANGLE" + "\n" +
                        "1 1 3");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
        });
        assertTrue(exception.getMessage().contains("Ошибка в текстовом файле: правило треугольника для задданных трех сторон не выполняется"));
    }
    @Test
    void InputFileReader_ExceptionFileEmpty() {
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
        });
        assertTrue(exception.getMessage().contains("Ошибка: текстовый файл пуст"));
    }
    @Test
    void InputFileReader_RightPositionRectangleSides() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
            bw.write("RECTANGLE" + "\n" +
                    "3 5");
        }
        InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
        Assertions.assertTrue(inFile.getSegment()[0] > inFile.getSegment()[1]);
    }

    @AfterAll
    public static void afterClass() {
        if (tmp == null) {
            return;
        }
        tmp.deleteOnExit();
    }
}

