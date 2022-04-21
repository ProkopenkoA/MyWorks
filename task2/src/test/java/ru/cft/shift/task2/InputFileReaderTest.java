package ru.cft.shift.task2;

import java.io.*;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class InputFileReaderTest {
    private static File tmp;

    @BeforeAll
    public static void beforeAll() throws IOException {
        tmp = Files.createTempFile("inputFile", null).toFile();
    }

    @Test
    void InputFileReader_ExceptionNotRightType() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("Circle" + "\n" +
                        "5 м");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
            inFile.checkFileInput();
        });
    }

    @Test
    void InputFileReader_ExceptionNotRightSegment() {
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("RECTANGLE" + "\n" +
                        "5 м one");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
            inFile.checkFileInput();
        });
        assertTrue(exception.getMessage().contains("Ошибка в текстовом файле: аргумент задан не верно"));
    }

    @Test
    void InputFileReader_ExceptionRuleTriangle() {
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
                bw.write("TRIANGLE" + "\n" +
                        "1 м 1 м 3 м");
            }
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
            inFile.checkFileInput();
        });
        assertTrue(exception.getMessage().contains("Ошибка в текстовом файле: правило треугольника для задданных трех сторон не выполняется"));
    }

    @Test
    void InputFileReader_NoException() throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp.getAbsolutePath()))) {
            bw.write("RECTANGLE" + "\n" +
                    "1 м 3 м");
            InputFileReader inFile = new InputFileReader(tmp.getAbsolutePath());
            inFile.checkFileInput();
        }
    }

    @AfterAll
    public static void afterClass() {
        if (tmp == null) {
            return;
        }
        tmp.deleteOnExit();
    }
}

