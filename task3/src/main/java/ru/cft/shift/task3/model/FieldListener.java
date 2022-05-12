package ru.cft.shift.task3.model;

public interface FieldListener {
    void changeField(int column, int row, FieldContent fieldContent);

    void createNewGameField(int length, int width, int numberOfMine);

    void changeMineCount(int numberOfMine);
}
