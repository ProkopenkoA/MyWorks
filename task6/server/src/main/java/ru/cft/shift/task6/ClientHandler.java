package ru.cft.shift.task6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static List<String> clientsNames = new ArrayList<String>();
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private Socket clientSocket = null;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            for (String name : clientsNames) {
                sendMsg("##ADD##NEW##NAME##" + name);
            }
            String clientMessage;
            while (true) {
                if (inMessage.hasNext()) {
                    clientMessage = inMessage.nextLine();
                    System.out.println(clientMessage);
                    if (clientMessage.length() > 19) {
                        if (clientMessage.startsWith("##ADD##NEW##NAME##")) {
                            if (hasThisName(clientMessage.substring(18))) {
                                sendMsg("Это имя уже занято - " + clientMessage.substring(18));
                                break;
                            }
                            server.sendMessageToAllClients(clientMessage);
                            clientMessage = clientMessage.substring(18);
                            clientsNames.add(clientMessage);
                            server.sendMessageToAllClients(clientMessage + ", Добро пожаловать в чат!");
                            continue;
                        }
                    }
                    if (clientMessage.length() > 17) {
                        if (clientMessage.startsWith("##session##end##")) {
                            clientMessage = clientMessage.substring(16);
                            int index;
                            for (index = 0; index < clientsNames.size(); index++) {
                                if (clientMessage.equals(clientsNames.get(index))) {
                                    clientsNames.remove(index);
                                    break;
                                }
                            }
                            server.sendMessageToAllClients("##client##get##out##" + index);
                            server.sendMessageToAllClients("Участник - " + clientMessage + ": Вышел!");
                            break;
                        }
                    }
                    server.sendMessageToAllClients(clientMessage);
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            log.error("Ошибка: " + ex);
        } finally {
            this.closeClient();
        }
    }

    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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