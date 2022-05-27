package ru.cft.shift.task5.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task5.item.Item;

import java.util.ArrayDeque;

public class StorageItem {
    private static final Logger log = LoggerFactory.getLogger(StorageItem.class);

    private ArrayDeque<Item> store;
    private final int maxItemInStorage;

    public StorageItem(int sizeStorage) {
        maxItemInStorage = sizeStorage;
        store = new ArrayDeque<Item>(sizeStorage);
    }

    public synchronized void add(Item element) {
        while (store.size() >= maxItemInStorage) {
            log.info(Thread.currentThread().getName() + " В режиме ожидания");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info(Thread.currentThread().getName() + " Продолжает работу");
        }
        store.addLast(element);
        log.info(Thread.currentThread().getName() + " Item №" + element.getNumber() + " Произведено");
        log.info("Ресурсов на складе - " + store.size());
        notify();
    }

    public synchronized Item get() {
        while (store.size() <= 0) {
            log.info(Thread.currentThread().getName() + " В режиме ожидания");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info(Thread.currentThread().getName() + " Продолжает работу");
        }
        Item item;
        item = store.removeFirst();
        log.info(Thread.currentThread().getName() + " Item №" + item.getNumber() + " Потреблено");
        log.info("Ресурсов на складе - " + store.size());
        notify();
        return item;
    }
}
