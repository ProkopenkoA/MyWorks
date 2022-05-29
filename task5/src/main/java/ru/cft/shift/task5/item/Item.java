package ru.cft.shift.task5.item;

public class Item {
    private static volatile int count = 0;

    private final int number;

    public Item() {
        synchronized (Item.class) {
            number = count++;
        }
    }

    public int getNumber() {
        return number;
    }
}
