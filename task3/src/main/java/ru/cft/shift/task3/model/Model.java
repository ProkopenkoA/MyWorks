package ru.cft.shift.task3.model;

import ru.cft.shift.task3.dto.GameType;
import ru.cft.shift.task3.model.secondmeter.SecondMeterInterface;

import java.awt.*;
import java.util.Random;

public class Model implements ModelInterface {

    FieldListener fieldListener;
    LoseGameListener loseGameListener;
    WinGameListener winGameListener;
    SecondMeterInterface secondMeter;

    Cell[][] field;
    private static int fieldLength = 9;
    private static int fieldWidth = 9;
    private static int numberOfMine;
    private int numberOfFlag = 0;
    GameType gameType;

    public Model() {
    }

    public void createNewField(GameType gameType) {
        switch (gameType) {
            case NOVICE -> createNewField(9, 9, 10);
            case MEDIUM -> createNewField(16, 16, 40);
            case EXPERT -> createNewField(16, 30, 99);
        }
        this.gameType = gameType;
    }

    public void createNewField(int rowSize, int columnSize, int numberOfMine) {
        fieldLength = columnSize;
        fieldWidth = rowSize;
        Model.numberOfMine = numberOfMine;
        numberOfFlag = 0;
        field = new Cell[fieldLength][fieldWidth];
        createMineField();
        fieldListener.createNewGameField(rowSize, columnSize, numberOfMine);
    }

    public void refreshMineField() {
        numberOfFlag = 0;
        createMineField();
        fieldListener.createNewGameField(fieldWidth, fieldLength, numberOfMine);
    }

    public void createNewFieldWithNoMine(int startX, int startY) {
        createMine(numberOfMine, startX, startY);
        createNumberField();
        secondMeter.stopSecondMeter();
        secondMeter.newStartSecondMeter();
    }

    private void createMineField() {
        for (int rowIndex = 0; rowIndex < fieldLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < fieldWidth; columnIndex++) {
                field[rowIndex][columnIndex] = new Cell();
                field[rowIndex][columnIndex].setCellContent(" ");
            }
        }
    }

    private void createMine(int num, int startX, int startY) {
        Random r = new Random();
        while (num > 0) {
            int x = r.nextInt(fieldLength);
            int y = r.nextInt(fieldWidth);
            if (!field[x][y].getContent().equals("*") && !(x == startX && y == startY)) {
                field[x][y].setCellContent("*");
                num--;
            }
        }
    }

    private Point[] getFieldAround(int x, int y) {
        Point[] cell = new Point[8];
        cell[0] = new Point(x, y - 1);
        cell[1] = new Point(x, y + 1);
        cell[2] = new Point(x - 1, y);
        cell[3] = new Point(x + 1, y);
        cell[4] = new Point(x - 1, y + 1);
        cell[5] = new Point(x + 1, y + 1);
        cell[6] = new Point(x - 1, y - 1);
        cell[7] = new Point(x + 1, y - 1);
        return cell;
    }

    private void createNumberField() {
        for (int rowIndex = 0; rowIndex < fieldLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < fieldWidth; columnIndex++) {
                int num = 0;
                if (!field[rowIndex][columnIndex].getContent().equals("*")) {
                    Point[] cells = this.getFieldAround(rowIndex, columnIndex);
                    for (int indexFields = 0; indexFields < 8; indexFields++) {
                        Point point = cells[indexFields];
                        if (point.x >= 0 && point.x < fieldLength && point.y >= 0 && point.y < fieldWidth) {
                            if (field[point.x][point.y].getContent().equals("*")) {
                                num++;
                            }
                        }
                    }
                }
                if (num > 0) {
                    field[rowIndex][columnIndex].setCellContent(String.valueOf(num));
                }
            }
        }
    }

    public void markMine(int row, int col) {
        if (!field[row][col].isOpen()) {
            field[row][col].changeFlag();
            if (field[row][col].isFlag()) {
                numberOfFlag++;
                fieldListener.changeCell(row, col, CellContent.MARKED);
                fieldListener.changeMineCount(numberOfMine - numberOfFlag);
            } else {
                numberOfFlag--;
                fieldListener.changeCell(row, col, CellContent.CLOSED);
                fieldListener.changeMineCount(numberOfMine - numberOfFlag);
            }
        }
    }

    public boolean isStepOnOpenNumber(int row, int col) {
        if (field[row][col].isOpen()) {
            if (!field[row][col].getContent().equals("*") && !field[row][col].getContent().equals(" ")) {
                tryAreaMine(row, col);
                return true;
            }
        }
        return false;
    }

    public boolean stepMineIsEnd(int row, int col) {
        if (field[row][col].isFlag()) {
            return false;
        }
        if (field[row][col].isOpen()) {
            return false;
        }
        field[row][col].setOpen(true);
        fieldListener.changeCell(row, col, field[row][col].getCellContent());
        switch (field[row][col].getContent()) {
            case "*" -> {
                secondMeter.stopSecondMeter();
                loseGameListener.youLose();
                return true;
            }
            case " " -> {
                Point[] fields = this.getFieldAround(row, col);
                for (int indexFields = 0; indexFields < 8; indexFields++) {
                    Point point = fields[indexFields];
                    if (point.x >= 0 && point.x < fieldLength && point.y >= 0 && point.y < fieldWidth) {
                        if (!field[point.x][point.y].isOpen()) {
                            if (stepMineIsEnd(point.x, point.y)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return maybeYouWin();
    }

    public void tryAreaMine(int row, int col) {
        if (!field[row][col].isOpen()) {
            return;
        }
        if (field[row][col].getContent().equals(" ")) {
            stepMineIsEnd(row, col);
            return;
        }
        int countMine = 0;
        Point[] cell = this.getFieldAround(row, col);
        for (int indexFields = 0; indexFields < 8; indexFields++) {
            Point point = cell[indexFields];
            if (point.x >= 0 && point.x < fieldLength && point.y >= 0 && point.y < fieldWidth) {
                if (field[point.x][point.y].isFlag()) {
                    countMine++;
                }
            }
        }
        if (Integer.parseInt(field[row][col].getContent()) == countMine) {
            for (int indexFields = 0; indexFields < 8; indexFields++) {
                Point point = cell[indexFields];
                if (point.x >= 0 && point.x < fieldLength && point.y >= 0 && point.y < fieldWidth) {
                    if (!field[point.x][point.y].isOpen()) {
                        if (stepMineIsEnd(point.x, point.y)) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean maybeYouWin() {
        for (int row = 0; row < fieldLength; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (!field[row][col].isOpen() && !field[row][col].getContent().equals("*")) {
                    return false;
                }
            }
        }
        secondMeter.stopWithResultSecondMeter(gameType);
        winGameListener.youWin();
        return true;
    }

    public void setWinGameListener(WinGameListener winGameListener) {
        this.winGameListener = winGameListener;
    }

    public void setFieldListener(FieldListener fieldListener) {
        this.fieldListener = fieldListener;
    }

    public void setLoseGameListener(LoseGameListener loseGameListener) {
        this.loseGameListener = loseGameListener;
    }

    public void setSecondMeter(SecondMeterInterface secondMeter) {
        this.secondMeter = secondMeter;
    }
}
