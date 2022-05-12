package ru.cft.shift.task3.model;

import ru.cft.shift.task3.dto.GameType;

public interface ChangeSecondMeterListener {
    void newStartSecondMeter();

    void stopSecondMeter();

    void stopWithResultSecondMeter(GameType gameType);
}
