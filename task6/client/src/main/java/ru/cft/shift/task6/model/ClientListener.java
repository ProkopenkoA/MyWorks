package ru.cft.shift.task6.model;

public interface ClientListener {
    void addClient(String nameClient);

    void deleteClient(int numClient);

    void deleteAllClient();
}
