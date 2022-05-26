package ru.cft.shift.task6.view;

import ru.cft.shift.task6.model.ChatListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements ChatListener {

    private MessegeListener messegeListener;
    private CloseServerListener closeServerListener;

    private JTextField jtfMessage;
    private JTextArea jtaTextAreaMessage;

    public MainWindow() {
        setBounds(600, 300, 600, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jtaTextAreaMessage = new JTextArea();
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);

        add(jsp, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSendMessage = new JButton("ќтправить");
        bottomPanel.add(jbSendMessage, BorderLayout.EAST);
        jtfMessage = new JTextField("¬ведите ваше сообщение: ");
        bottomPanel.add(jtfMessage, BorderLayout.CENTER);

        jbSendMessage.addActionListener(e -> {
            if (!jtfMessage.getText().trim().isEmpty()) {
                messegeListener.sendMsg(jtfMessage.getText());
                jtfMessage.grabFocus();
            }
        });
        jtfMessage.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMessage.setText("");
            }
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeServerListener.closeServer();
                super.windowClosing(e);
            }
        });
    }

    public void setMessegeListener(MessegeListener listener) {
        this.messegeListener = listener;
    }

    public void setCloseServerListener(CloseServerListener closeServerListener) {
        this.closeServerListener = closeServerListener;
    }

    @Override
    public void openMainWindow(boolean open) {
        setVisible(open);
    }

    @Override
    public void printMsg(String msg) {
        jtaTextAreaMessage.append(msg + "\n");
    }
}