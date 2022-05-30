package ru.cft.shift.task6.view;

import ru.cft.shift.task6.model.ChatListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ChatListener {

    private MessegeListener messegeListener;
    private CloseServerListener closeServerListener;

    private JMenuItem newServer;
    private JMenuItem exitMenu;

    private JTextField jtfMessage;
    private JTextArea jtaTextAreaMessage;
    private JScrollPane jsp;

    public MainWindow() {
        setBounds(600, 300, 600, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createSetting();

        jtaTextAreaMessage = new JTextArea();
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        jsp = new JScrollPane(jtaTextAreaMessage);

        add(jsp, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.SOUTH);
        JButton jbSendMessage = new JButton("Отправить");
        bottomPanel.add(jbSendMessage, BorderLayout.EAST);
        jtfMessage = new JTextField("Введите ваше сообщение: ");
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

    private void createSetting(){
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Настройки");

        gameMenu.add(newServer = new JMenuItem("Новый сервер"));
        gameMenu.addSeparator();
        gameMenu.add(exitMenu = new JMenuItem("Выход"));

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
    }

    public void setNewServer(ActionListener listener) {
        newServer.addActionListener(listener);
    }

    public void setExitMenuAction(ActionListener listener) {
        exitMenu.addActionListener(listener);
    }

    public void setMessageListener(MessegeListener listener) {
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
        JScrollBar bar = jsp.getVerticalScrollBar();
        bar.setValue(bar.getMaximum());
    }
}