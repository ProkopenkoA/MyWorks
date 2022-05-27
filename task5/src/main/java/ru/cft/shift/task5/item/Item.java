package ru.cft.shift.task5.item;

public class Item {
    private final int  number;
    private static int count = 0;

    public Item() {
        count++;
        number = count;
    }

    public int getNumber() {
        return number;
    }
}
