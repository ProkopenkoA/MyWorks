package ru.cft.shift.task3.controller;

import ru.cft.shift.task3.dto.ButtonType;
import ru.cft.shift.task3.dto.GameType;
import ru.cft.shift.task3.model.Model;
import ru.cft.shift.task3.model.ModelInterface;
import ru.cft.shift.task3.view.GameTypeListener;

public class ModelController implements GameTypeListener {
    private final ModelInterface modelInterface;

    private boolean isFirstClick = true;

    public ModelController(Model modelInterface) {
        this.modelInterface = modelInterface;

    }public void newGame() {
        modelInterface.refreshMineField();
        isFirstClick = true;
    }

    public void onMouseClick(int row, int col, ButtonType buttonType) {
        if (isFirstClick) {
            isFirstClick = false;
            modelInterface.createNewFieldWithNoMine(row, col);
        }
        switch (buttonType) {
            case LEFT_BUTTON -> {
                if (!modelInterface.isStepOnOpenNumber(row, col)) {
                    modelInterface.stepMineIsEnd(row, col);
                }
            }
            case RIGHT_BUTTON -> modelInterface.markMine(row, col);
            case MIDDLE_BUTTON -> modelInterface.tryAreaMine(row, col);
        }
    }

    public void onGameTypeChanged(GameType gameType) {
        modelInterface.createNewField(gameType);
        isFirstClick = true;
    }
}
