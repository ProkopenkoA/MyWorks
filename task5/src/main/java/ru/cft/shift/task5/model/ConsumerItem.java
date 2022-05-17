package ru.cft.shift.task5.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerItem implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ConsumerItem.class.getName());

    private final StorageItem storageItem;
    private final int consumerTime;

    public ConsumerItem(StorageItem storageItem, int consumerTime) {
        this.storageItem = storageItem;
        this.consumerTime = consumerTime;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(consumerTime);
            } catch (InterruptedException e) {
                log.info("Interrupt " + e.getMessage());
                break;
            }
            storageItem.get();
        }
    }
}


