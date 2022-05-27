package ru.cft.shift.task6;

import ru.cft.shift.task6.controller.Controller;
import ru.cft.shift.task6.model.Model;
import ru.cft.shift.task6.view.ConnectWindow;
import ru.cft.shift.task6.view.ListClient;
import ru.cft.shift.task6.view.MainWindow;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        ConnectWindow connectWindow = new ConnectWindow();
        MainWindow mainWindow = new MainWindow();
        ListClient listClient = new ListClient(mainWindow);

        model.setConnectServerListener(connectWindow);
        model.setChatListener(mainWindow);
        model.setClientListener(listClient);
        mainWindow.setMessegeListener(controller::sendMsg);
        mainWindow.setCloseServerListener(controller::closeServer);
        connectWindow.setNewServerListener(controller::connectToServer);

        connectWindow.setVisible(true);
    }
}