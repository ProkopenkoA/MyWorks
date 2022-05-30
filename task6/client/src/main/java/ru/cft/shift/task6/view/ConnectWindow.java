package ru.cft.shift.task6.view;

import ru.cft.shift.task6.model.ConnectServerListener;

import javax.swing.*;
import java.awt.*;

public class ConnectWindow extends JDialog implements ConnectServerListener {
    private NewServerListener listener;

    public ConnectWindow() {

        JTextField nameField = new JTextField();
        JTextField serverField = new JTextField();

        GridLayout layout = new GridLayout(5, 1);
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(new JLabel("¬ведите свое им€: "));
        contentPane.add(nameField);

        contentPane.add(new JLabel("¬ведите адрес сервера: "));
        contentPane.add(serverField);

        contentPane.add(createOkButton(nameField, serverField));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(210, 160));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setNewServerListener(NewServerListener listener) {
        this.listener = listener;
    }

    private JButton createOkButton(JTextField nameField, JTextField serverField) {
        JButton button = new JButton("OK");
        button.addActionListener(e -> listener.connectToServer(nameField.getText(), serverField.getText()));
        return button;
    }

    public void errorWindow(String str) {
        JOptionPane.showMessageDialog(null,
                str,
                "ќшибка",
                JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void openWindow(boolean open) {
        setVisible(open);
    }
}
