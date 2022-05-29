package ru.cft.shift.task3.model.secondmeter;

import ru.cft.shift.task3.dto.GameType;

public interface SecondMeterInterface {
    void newStartSecondMeter();
    void stopSecondMeter();
    void stopWithResultSecondMeter(GameType gameType);
    void setHighScoreName(String name);
}
