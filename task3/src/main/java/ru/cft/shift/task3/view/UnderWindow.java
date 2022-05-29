package ru.cft.shift.task3.view;

public class UnderWindow {

    SettingsWindow settingsWindow;
    HighScoresWindow highScoresWindow;
    RecordsWindow recordsWindow;
    LoseWindow loseWindow;
    WinWindow winWindow;

    public UnderWindow(MainWindow mainWindow) {
        settingsWindow = new SettingsWindow(mainWindow);
        highScoresWindow = new HighScoresWindow(mainWindow);
        recordsWindow = new RecordsWindow(mainWindow);
        loseWindow = new LoseWindow(mainWindow);
        winWindow = new WinWindow(mainWindow);
    }

    public SettingsWindow getSettingsWindow() {
        return settingsWindow;
    }

    public HighScoresWindow getHighScoresWindow() {
        return highScoresWindow;
    }

    public RecordsWindow getRecordsWindow() {
        return recordsWindow;
    }

    public LoseWindow getLoseWindow() {
        return loseWindow;
    }

    public WinWindow getWinWindow() {
        return winWindow;
    }
}
