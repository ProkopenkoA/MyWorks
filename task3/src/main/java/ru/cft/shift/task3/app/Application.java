package ru.cft.shift.task3.app;


import ru.cft.shift.task3.controller.ModelController;
import ru.cft.shift.task3.controller.SecondMeterController;
import ru.cft.shift.task3.dto.GameType;
import ru.cft.shift.task3.model.Model;
import ru.cft.shift.task3.model.secondmeter.SecondMeter;
import ru.cft.shift.task3.view.*;

public class Application {

    public static void main(String[] args) {
        Model model = new Model();
        SecondMeter secondMeter = new SecondMeter();

        ModelController modelController = new ModelController(model);
        SecondMeterController secondMeterController = new SecondMeterController(secondMeter);

        MainWindow mainWindow = new MainWindow();
        UnderWindow underWindow = new UnderWindow(mainWindow);

        model.setSecondMeter(secondMeter);
        model.setFieldListener(mainWindow);
        model.setLoseGameListener(underWindow.getLoseWindow());
        model.setWinGameListener(underWindow.getWinWindow());
        secondMeter.setSecondListener(mainWindow);
        secondMeter.setHighScoreListener(underWindow.getHighScoresWindow());
        secondMeter.setHighScoreNameListener(underWindow.getRecordsWindow());
        underWindow.getSettingsWindow().setGameTypeListener(modelController);
        underWindow.getRecordsWindow().setNameListener(secondMeterController);


        mainWindow.setNewGameMenuAction(e -> modelController.newGame());
        underWindow.getLoseWindow().setNewGameListener(e -> modelController.newGame());
        underWindow.getWinWindow().setNewGameListener(e -> modelController.newGame());
        mainWindow.setSettingsMenuAction(e -> underWindow.getSettingsWindow().setVisible(true));
        mainWindow.setHighScoresMenuAction(e -> underWindow.getHighScoresWindow().setVisible(true));
        mainWindow.setExitMenuAction(e -> {
            mainWindow.dispose();
            secondMeterController.stop();
        });
        underWindow.getLoseWindow().setExitListener(e -> {
            mainWindow.dispose();
            secondMeterController.stop();
        });
        underWindow.getWinWindow().setExitListener(e -> {
            mainWindow.dispose();
            secondMeterController.stop();
        });
        mainWindow.setCellListener(modelController::onMouseClick);

        mainWindow.setVisible(true);
        model.createNewField(GameType.NOVICE);
    }
}
