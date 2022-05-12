package ru.cft.shift.task3.model;

import ru.cft.shift.task3.dto.GameType;

public class SecondMeter implements ChangeSecondMeterListener {
    MyThread myThread = new MyThread();
    SecondListener secondListener;
    HighScore highScore = new HighScore();

    @Override
    public void newStartSecondMeter() {
        myThread = new MyThread(secondListener);
        myThread.start();
    }

    @Override
    public void stopSecondMeter() {
        myThread.interrupt();
    }

    @Override
    public void stopWithResultSecondMeter(GameType gameType) {
        myThread.interrupt();
        highScore.setNewHighScore(gameType, myThread.getSecondTime() - 1);
    }


    public void setSecondListener(SecondListener secondListener) {
        this.secondListener = secondListener;
        myThread = new MyThread(secondListener);
    }

    public void setHighScoreListener(HighScoreListener highScoreListener) {
        highScore.setHighScoreListener(highScoreListener);
    }

    public void setHighScoreNameListener(HighScoreNameListener highScoreNameListener) {
        highScore.setHighScoreNameListener(highScoreNameListener);
    }

    public void setHighScoreName(String name) {
        highScore.setNameWinner(name);
    }

    public void setOldHighScore() {
        highScore.setOldHighScore();
    }
}
