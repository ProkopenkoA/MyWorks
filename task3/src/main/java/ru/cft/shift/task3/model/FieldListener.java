package ru.cft.shift.task3.model;

public interface FieldListener {
    void changeCell(int column, int row, CellContent cellContent);

    void createNewGameField(int length, int width, int numberOfMine);

    void changeMineCount(int numberOfMine);
}
