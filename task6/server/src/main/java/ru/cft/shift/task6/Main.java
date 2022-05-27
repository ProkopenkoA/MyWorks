package ru.cft.shift.task6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(ClassLoader.getSystemResource("config.properties").getPath())) {
            properties.load(fis);
            Server server = new Server(Integer.parseInt(properties.getProperty("serverPort")));
            log.info("Сервер запущен");
        }catch (IOException | NumberFormatException e){
            log.error("Ошибка: " + e);
        }
    }
}
