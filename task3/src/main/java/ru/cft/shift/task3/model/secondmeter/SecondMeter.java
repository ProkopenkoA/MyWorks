package ru.cft.shift.task3.model.secondmeter;

import ru.cft.shift.task3.dto.GameType;

public class SecondMeter implements SecondMeterInterface {
    SecondsCycle secondsCycle = new SecondsCycle();
    SecondListener secondListener;
    HighScore highScore = new HighScore();

    @Override
    public void newStartSecondMeter() {
        secondsCycle = new SecondsCycle(secondListener);
        secondsCycle.start();
    }

    @Override
    public void stopSecondMeter() {
        secondsCycle.interrupt();
    }

    @Override
    public void stopWithResultSecondMeter(GameType gameType) {
        secondsCycle.interrupt();
        highScore.setNewHighScore(gameType, secondsCycle.getSecondTime() - 1);
    }


    public void setSecondListener(SecondListener secondListener) {
        this.secondListener = secondListener;
        secondsCycle = new SecondsCycle(secondListener);
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
