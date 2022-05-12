package ru.cft.shift.task3.model;

import ru.cft.shift.task3.dto.GameType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {


    private static int novice = 999;
    private static int medium = 999;
    private static int expert = 999;

    private String nameWinner = "";

    private HighScoreListener highScoreListener;
    private HighScoreNameListener highScoreNameListener;

    private String[] highScoreResult = new String[]{"Unknown", "999", "Unknown", "999", "Unknown", "999",};

    public HighScore() {
        setHighScoreResult();
    }

    private void setHighScoreResult() {
        File filePath = new File("ResultMineSweeper");
        filePath.mkdir();
        File file = new File(filePath + "\\result.txt");
        try {
            if (!file.createNewFile()) {
                FileReader fr = new FileReader(file);
                Scanner scanner = new Scanner(fr);
                for (int count = 0; count < 6; count++) {
                    if (scanner.hasNext()) {
                        highScoreResult[count] = scanner.next();
                    }
                }
                scanner.close();
                novice = Integer.parseInt(highScoreResult[1]);
                medium = Integer.parseInt(highScoreResult[3]);
                expert = Integer.parseInt(highScoreResult[5]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHighScoreNameListener(HighScoreNameListener highScoreNameListener) {
        this.highScoreNameListener = highScoreNameListener;
    }

    public void setHighScoreListener(HighScoreListener highScoreListener) {
        this.highScoreListener = highScoreListener;
        setOldHighScore();
    }

    public void setOldHighScore() {

        highScoreListener.setNoviceRecord(highScoreResult[0], Integer.parseInt(highScoreResult[1]));
        highScoreListener.setMediumRecord(highScoreResult[2], Integer.parseInt(highScoreResult[3]));
        highScoreListener.setExpertRecord(highScoreResult[4], Integer.parseInt(highScoreResult[5]));

    }

    public void setNameWinner(String nameWinner) {
        this.nameWinner = nameWinner;
    }

    public void setNewHighScore(GameType gameType, int secondTime) {
        switch (gameType) {
            case NOVICE -> {
                if (novice > secondTime) {
                    novice = secondTime;
                    highScoreNameListener.newNameListener();
                    highScoreListener.setNoviceRecord(nameWinner, secondTime);
                    writeNewResult(gameType, secondTime, nameWinner);
                }
            }
            case MEDIUM -> {
                if (medium > secondTime) {
                    medium = secondTime;
                    highScoreNameListener.newNameListener();
                    highScoreListener.setMediumRecord(nameWinner, secondTime);
                    writeNewResult(gameType, secondTime, nameWinner);
                }
            }
            case EXPERT -> {
                if (expert > secondTime) {
                    expert = secondTime;
                    highScoreNameListener.newNameListener();
                    highScoreListener.setExpertRecord(nameWinner, secondTime);
                    writeNewResult(gameType, secondTime, nameWinner);
                }
            }
        }
    }

    public void writeNewResult(GameType gameType, int secondTime, String name) {
        switch (gameType) {
            case NOVICE -> {
                highScoreResult[0] = name;
                highScoreResult[1] = String.valueOf(secondTime);
            }
            case MEDIUM -> {
                highScoreResult[2] = name;
                highScoreResult[3] = String.valueOf(secondTime);
            }
            case EXPERT -> {
                highScoreResult[4] = name;
                highScoreResult[5] = String.valueOf(secondTime);
            }
        }
        writeHighScoreResult();
    }

    public void writeHighScoreResult() {
        File filePath = new File("ResultMineSweeper");
        File file = new File(filePath + "\\result.txt");
        String result = "";
        for (String str : highScoreResult) {
            result = result + str + "\n";
        }
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
