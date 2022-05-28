package ru.cft.shift.task6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.shift.task6.common.Message;
import ru.cft.shift.task6.common.MessageType;
import ru.cft.shift.task6.common.MsgConvert;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

    private static List<String> clientsNames = new ArrayList<String>();

    private final MsgConvert msgConvert = new MsgConvert();
    private Message message;
    private boolean isEnd = true;

    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;


    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (isEnd) {
                if (inMessage.hasNext()) {
                    message = msgConvert.covertToMessage(inMessage.nextLine());
                    switch (message.getMessageType()) {
                        case ADD -> {
                            if (hasThisName(message.getMsg())) {
                                sendMsg(MessageType.NOT_FIRST, message.getMsg());
                                return;
                            }
                            server.sendMessageToAllClients(MessageType.ADD, message.getMsg());
                            clientsNames.add(message.getMsg());
                            server.sendMessageToAllClients(MessageType.CLIENT, message.getMsg() + ", Добро пожаловать в чат!");
                        }
                        case DELETE -> {
                            server.sendMessageToAllClients(MessageType.DELETE, String.valueOf(findNumClient(message.getMsg())));
                            server.sendMessageToAllClients(MessageType.CLIENT, "Участник - " + message.getMsg() + ": Вышел!");
                            isEnd = false;
                        }
                        case CLIENT -> server.sendMessageToAllClients(MessageType.CLIENT, message.getMsg());
                    }
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            log.error("Ошибка: " + ex);
        } finally {
            this.closeClient();
        }
    }


    public void sendMsg(MessageType messageType, String msg) {
        try {
            outMessage.println(msgConvert.convertToString(messageType, msg));
            outMessage.flush();
        } catch (Exception ex) {
            log.error("ошибка при отправки: " + ex);
        }
    }

    public void sendAllClients() {
        for (String name : clientsNames) {
            sendMsg(MessageType.ADD, name);
        }
    }

    private int findNumClient(String name) {
        int index;
        for (index = 0; index < clientsNames.size(); index++) {
            if (name.equals(clientsNames.get(index))) {
                clientsNames.remove(index);
                return index;
            }
        }
        return 0;
    }

    private boolean hasThisName(String clientName) {
        for (String name : clientsNames) {
            if (name.equals(clientName)) {
                return true;
            }
        }
        return false;
    }

    private void closeClient() {
        server.removeClient(this);
    }
}