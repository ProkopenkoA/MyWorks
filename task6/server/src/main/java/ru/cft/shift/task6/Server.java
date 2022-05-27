package ru.cft.shift.task6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private int port = 3443;
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public Server(int portServer) {
        this.port = portServer;
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(this.port);
            log.info("Сервер запущен!");
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException ex) {
            log.error("Ошибка сервера: " + ex);
        } finally {
            try {
                assert clientSocket != null;
                clientSocket.close();
                serverSocket.close();
            } catch (IOException ex) {
                log.error("Ошибка сервера: " + ex);
            }
        }
    }

    public void sendMessageToAllClients(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }

    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

}
