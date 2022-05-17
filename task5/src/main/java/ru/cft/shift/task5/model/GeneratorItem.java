package ru.cft.shift.task5.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task5.item.Item;

public class GeneratorItem implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(GeneratorItem.class.getName());

    private final StorageItem storageItem;
    private final int producerTime;

    public GeneratorItem(StorageItem storageItem, int producerTime) {
        this.storageItem = storageItem;
        this.producerTime = producerTime;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(producerTime);
            } catch (InterruptedException e) {
                log.error("Interrupt " + e.getMessage());
                break;
            }
            storageItem.add(new Item());
        }
    }
}

