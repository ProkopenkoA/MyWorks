package ru.cft.shift.task3.app;


import ru.cft.shift.task3.controller.Controller;
import ru.cft.shift.task3.dto.GameType;
import ru.cft.shift.task3.model.Model;
import ru.cft.shift.task3.model.SecondMeter;
import ru.cft.shift.task3.view.*;

public class Application {

    public static void main(String[] args) {
        Model model = new Model();
        SecondMeter secondMeter = new SecondMeter();
        Controller controller = new Controller(model, secondMeter);
        MainWindow mainWindow = new MainWindow();
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
        RecordsWindow recordsWindow = new RecordsWindow(mainWindow);
        LoseWindow loseWindow = new LoseWindow(mainWindow);
        WinWindow winWindow = new WinWindow(mainWindow);

        model.setSecondMeter(secondMeter);
        model.setFieldListener(mainWindow);
        model.setLoseGameListener(loseWindow);
        model.setWinGameListener(winWindow);
        secondMeter.setSecondListener(mainWindow);
        secondMeter.setHighScoreListener(highScoresWindow);
        secondMeter.setHighScoreNameListener(recordsWindow);
        settingsWindow.setGameTypeListener(controller);
        recordsWindow.setNameListener(controller);


        mainWindow.setNewGameMenuAction(e -> controller.newGame());
        loseWindow.setNewGameListener(e -> controller.newGame());
        winWindow.setNewGameListener(e -> controller.newGame());
        mainWindow.setSettingsMenuAction(e -> settingsWindow.setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> highScoresWindow.setVisible(true));
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        loseWindow.setExitListener(e -> mainWindow.dispose());
        winWindow.setExitListener(e -> mainWindow.dispose());
        mainWindow.setCellListener(controller::onMouseClick);

        mainWindow.setVisible(true);
        model.createNewField(GameType.NOVICE);
    }
}
