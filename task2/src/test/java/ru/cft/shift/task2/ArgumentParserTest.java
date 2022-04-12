package ru.cft.shift.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ArgumentParserTest {
    ArgumentParser argParser;

    @Test
    void argumentParser_ExceptionCountOfArgs() {
        String[] args = new String[]{"-c"};
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            argParser = new ArgumentParser(args);
            argParser.checkArgument();
        });
        assertTrue(exception.getMessage().contains("Ошибка входных данных: кол-во параметров задано не верно"));
    }

    @Test
    void argumentParser_ExceptionNoTilda() {
        String[] args = new String[]{"-c", "i", "Input.txt"};
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            argParser = new ArgumentParser(args);
            argParser.checkArgument();
        });
        assertTrue(exception.getMessage().contains("Ошибка ввода входных данных: параметр начинается не с '-'"));
    }

    @Test
    void argumentParser_ExceptionNotRightParameter() {
        String[] args = new String[]{"-c", "-f", "Input.txt"};
        Exception exception = Assertions.assertThrows(MyException.class, () -> {
            argParser = new ArgumentParser(args);
            argParser.checkArgument();
        });
        assertTrue(exception.getMessage().contains("Ошибка ввода входных данных: параметр не найден"));
    }

    @Test
    void argumentParser_NotThrowException() {
        File tmp = null;
        try {
            tmp = Files.createTempFile("inputFile", null).toFile();
            File finalTmp = tmp;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(finalTmp.getAbsolutePath()))) {
                bw.write("CIRCLE" + "\n" +
                        "5 м");
            }
            String[] args = new String[]{"-c", "-i", finalTmp.getAbsolutePath()};
            argParser = new ArgumentParser(args);
            argParser.checkArgument();
        } catch (Exception e) {
            fail("Ошибок не должно быть");
        } finally {
            if (tmp != null) {
                tmp.deleteOnExit();
            }
        }

    }
}
