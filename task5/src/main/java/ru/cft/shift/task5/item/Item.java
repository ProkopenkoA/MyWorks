package ru.cft.shift.task5.item;

public class Item {
    private static volatile int count = 0;

    private int number;

    public Item() {
        createNum();
    }

    private synchronized void createNum(){
        number = count++;
    }

    public int getNumber() {
        return number;
    }
}
