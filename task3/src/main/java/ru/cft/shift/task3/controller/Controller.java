package ru.cft.shift.task3.controller;

import ru.cft.shift.task3.model.Model;
import ru.cft.shift.task3.dto.ButtonType;
import ru.cft.shift.task3.dto.GameType;
import ru.cft.shift.task3.model.SecondMeter;
import ru.cft.shift.task3.view.GameTypeListener;
import ru.cft.shift.task3.view.RecordNameListener;


public class Controller implements GameTypeListener, RecordNameListener {

    private final Model model;
    private final SecondMeter secondMeter;

    private boolean isFirstClick = true;

    public Controller(Model model, SecondMeter secondMeter) {
        this.model = model;
        this.secondMeter = secondMeter;
    }

    public void newGame() {
        model.refreshMineField();
        isFirstClick = true;
    }

    public void onMouseClick(int row, int col, ButtonType buttonType) {
        if (isFirstClick) {
            isFirstClick = false;
            model.createNewFieldWithNoMine(row, col);
        }
        switch (buttonType) {
            case LEFT_BUTTON -> {
                if (!model.isStepOnOpenNumber(row, col)) {
                    model.stepMineIsEnd(row, col);
                }
            }
            case RIGHT_BUTTON -> model.markMine(row, col);
            case MIDDLE_BUTTON -> model.tryAreaMine(row, col);
        }
    }

    public void onGameTypeChanged(GameType gameType) {
        model.createNewField(gameType);
        isFirstClick = true;
    }

    public void onRecordNameEntered(String name) {
        secondMeter.setHighScoreName(name);
    }
}
