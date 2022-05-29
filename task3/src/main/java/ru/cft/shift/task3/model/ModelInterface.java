package ru.cft.shift.task3.model;

import ru.cft.shift.task3.dto.GameType;

public interface ModelInterface {
    void refreshMineField();
    void createNewFieldWithNoMine(int row, int col);
    boolean isStepOnOpenNumber(int row, int col);
    boolean stepMineIsEnd(int row, int col);
    void markMine(int row, int col);
    void tryAreaMine(int row, int col);
    void createNewField(GameType gameType);
}
