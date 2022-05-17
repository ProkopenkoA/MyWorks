package ru.cft.shift.task5.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task5.item.Item;

import java.util.ArrayList;
import java.util.List;

public class StorageItem {
    private static final Logger log = LoggerFactory.getLogger(StorageItem.class.getName());


    private List<Item> store;
    private static final int MIN_ITEM_IN_STORAGE = 0;

    private final int maxItemInStorage;
    private int itemCounter = 0;
    private int itemNumber = 1;


    public StorageItem(int sizeStorage) {
        maxItemInStorage = sizeStorage;
        store = new ArrayList<>();
    }

    public synchronized void add(Item element) {

        try {
            if (itemCounter < maxItemInStorage) {
                element.setNumber(itemNumber);
                store.add(element);
                log.info(Thread.currentThread().getName() + " Item №" + element.getNumber() + " Произведено");
                itemCounter++;
                itemNumber++;
                log.info("Ресурсов на складе - " + itemCounter);
                notifyAll();
            } else {
                log.info(Thread.currentThread().getName() + " В режиме ожидания");
                wait();
                log.info(Thread.currentThread().getName() + " Продолжает работу");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Item get() {
        try {
            if (itemCounter > MIN_ITEM_IN_STORAGE) {
                for (Item item : store) {
                    itemCounter--;
                    log.info(Thread.currentThread().getName() + " Item №" + item.getNumber() + " Потреблено");
                    store.remove(item);
                    log.info("Ресурсов на складе - " + itemCounter);
                    notifyAll();
                    return item;
                }
            } else {
                log.info(Thread.currentThread().getName() + " В режиме ожидания");
                wait();
                log.info(Thread.currentThread().getName() + " Продолжает работу");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
