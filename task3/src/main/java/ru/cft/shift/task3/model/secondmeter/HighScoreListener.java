package ru.cft.shift.task3.model.secondmeter;

public interface HighScoreListener {
    void setNoviceRecord(String winnerName, int timeValue);

    void setMediumRecord(String winnerName, int timeValue);

    void setExpertRecord(String winnerName, int timeValue);
}
