package ru.cft.shift.task6.controller;

import ru.cft.shift.task6.model.Model;

public class Controller {
    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void sendMsg(String msg) {
        model.sendToServer(msg);
    }

    public void connectToServer(String name, String server) {
        model.connectToServer(name, server);
    }

    public void closeServer() {
        model.closeServer();
    }

}
