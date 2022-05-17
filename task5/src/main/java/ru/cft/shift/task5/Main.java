package ru.cft.shift.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task5.model.ConsumerItem;
import ru.cft.shift.task5.model.GeneratorItem;
import ru.cft.shift.task5.model.StorageItem;
import ru.cft.shift.task5.model.ThreadGenerate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) {

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("task5/src/main/resources/config.properties")) {
            properties.load(fis);

            StorageItem storageItem = new StorageItem(Integer.parseInt(properties.getProperty("storageSize")));
            GeneratorItem generatorItem = new GeneratorItem(storageItem, Integer.parseInt(properties.getProperty("producerTime")));
            ConsumerItem consumerItem = new ConsumerItem(storageItem, Integer.parseInt(properties.getProperty("consumerTime")));

            ThreadGenerate threadGenerate = new ThreadGenerate(consumerItem, generatorItem);
            threadGenerate.createThreads(Integer.parseInt(properties.getProperty("producerCount")), Integer.parseInt(properties.getProperty("consumerCount")));

        } catch (IOException e) {
            log.error("Ошибка при чтение properties файла - " + e);
        } catch (NumberFormatException e){
            log.error("Ошибка при проверке параметров - " + e);
        }
    }
}
