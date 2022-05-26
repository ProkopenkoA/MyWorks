package ru.cft.shift.task6.model;

public interface ChatListener {
    void openMainWindow(boolean open);

    void printMsg(String msg);
}
