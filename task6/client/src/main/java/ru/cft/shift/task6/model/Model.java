package ru.cft.shift.task6.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class Model {
    private static final Logger log = LoggerFactory.getLogger(Model.class);


    private static int serverPort = 3443;
    private static String serverHost = "localhost";
    private ClientListener clientListener;
    private ChatListener chatListener;
    private ConnectServerListener connectServerListener;
    private Socket clientSocket;
    private Scanner inMessage;
    private PrintWriter outMessage;
    private String clientName = "";

    public void connectToServer(String name, String server) {
        if (name.isEmpty()) {
            connectServerListener.errorWindow("Введите имя!");
            return;
        }
        clientName = name;
        if (!server.isEmpty()) {
            serverHost = server;
        }

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(ClassLoader.getSystemResource("config.properties").getPath())) {
            properties.load(fis);
            serverPort = Integer.parseInt(properties.getProperty("serverPort"));
        } catch (IOException | NumberFormatException e) {
         log.error("Ошибка в конфигурационном файле: " + e);
        }
        try {
            clientSocket = new Socket(serverHost, serverPort);
            new Socket();
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
            connectServerListener.openWindow(false);
            chatListener.openMainWindow(true);
            waitForMessage();
            outMessage.println("##ADD##NEW##NAME##" + clientName);
            outMessage.flush();
        } catch (IOException e) {
            connectServerListener.errorWindow("Не удалось подключиться к серверу");
            log.error("Ошибка подключения: " + e);
        }
    }

    public void waitForMessage() {
        new Thread(() -> {
            while (true) {
                if (inMessage.hasNext()) {
                    String inMes = inMessage.nextLine();

                    if (inMes.length() > 21) {
                        if (inMes.startsWith("Это имя уже занято - ")) {
                            clientListener.deleteAllClient();
                            chatListener.openMainWindow(false);
                            closeServer();
                            connectServerListener.openWindow(true);
                            connectServerListener.errorWindow(inMes);
                            break;
                        }
                    }

                    if (inMes.length() > 20) {
                        if (inMes.startsWith("##client##get##out##")) {
                            int index = Integer.parseInt(inMes.substring(20));
                            clientListener.deleteClient(index);
                            continue;
                        }
                    }

                    if (inMes.length() > 18) {
                        if (inMes.startsWith("##ADD##NEW##NAME##")) {
                            inMes = inMes.substring(18);
                            clientListener.addClient(inMes);
                            continue;
                        }
                    }

                    chatListener.printMsg(inMes);
                }
            }
        }).start();
    }

    public void closeServer() {
        try {
            outMessage.println("##session##end##" + clientName);
            outMessage.flush();
            outMessage.close();
            inMessage.close();
            clientSocket.close();
        } catch (IOException e) {
            log.error("Ошибка сервер: " + e);
        }
    }

    public void sendToServer(String message) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("[MM.dd hh:mm:ss a] ");
        outMessage.println(formatForDateNow.format(dateNow) + clientName + ": " + message);
        outMessage.flush();
    }

    public void setConnectServerListener(ConnectServerListener connectServerListener) {
        this.connectServerListener = connectServerListener;
    }

    public void setChatListener(ChatListener chatListener) {
        this.chatListener = chatListener;
    }

    public void setClientListener(ClientListener clientListener) {
        this.clientListener = clientListener;
    }
}
