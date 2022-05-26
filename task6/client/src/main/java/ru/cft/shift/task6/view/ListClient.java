package ru.cft.shift.task6.view;

import ru.cft.shift.task6.model.ClientListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListClient extends JFrame implements ClientListener {

    private static ArrayList<JLabel> labels = new ArrayList<>();
    private final MainWindow mainWindow;
    private JScrollPane scrollPane;
    private JPanel labPanel;

    public ListClient(MainWindow mainWindow) throws HeadlessException {
        this.mainWindow = mainWindow;
        createListClients();
    }

    public void createListClients() {
        JPanel titlePanel = new JPanel();
        titlePanel.add(new JLabel("Список участников:  "));

        labPanel = new JPanel();
        scrollPane = new JScrollPane(labPanel);
        labPanel.setLayout(new BoxLayout(labPanel, BoxLayout.Y_AXIS));

        JPanel jPanel = new JPanel(new BorderLayout());

        jPanel.add(titlePanel, BorderLayout.NORTH);
        jPanel.add(scrollPane, BorderLayout.CENTER);

        mainWindow.add(jPanel, BorderLayout.EAST);

    }

    public void addClient(String name) {
        JLabel label = new JLabel(name);
        labels.add(label);
        label.setAlignmentX(JLabel.HORIZONTAL);
        labPanel.add(label);
        scrollPane.revalidate();
    }

    public void deleteClient(int index) {
        if (labels.size() > 0) {
            JLabel label = labels.remove(index);
            labPanel.remove(label);
            labPanel.repaint();
            scrollPane.revalidate();
        }
    }

    @Override
    public void deleteAllClient() {
        int index = labels.size() - 1;
        while (index >= 0) {
            JLabel label = labels.remove(index);
            labPanel.remove(label);
            labPanel.repaint();
            scrollPane.revalidate();
            index--;
        }
    }
}
