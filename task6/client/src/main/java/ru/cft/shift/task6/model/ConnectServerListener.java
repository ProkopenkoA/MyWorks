package ru.cft.shift.task6.model;

public interface ConnectServerListener {
    void openWindow(boolean open);

    void errorWindow(String str);
}
