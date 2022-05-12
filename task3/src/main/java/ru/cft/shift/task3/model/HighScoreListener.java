package ru.cft.shift.task3.model;

public interface HighScoreListener {
    void setNoviceRecord(String winnerName, int timeValue);

    void setMediumRecord(String winnerName, int timeValue);

    void setExpertRecord(String winnerName, int timeValue);
}
