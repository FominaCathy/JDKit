package FominaKat.Chat.Server;

import FominaKat.Chat.Client.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerGUI extends JFrame implements ServerView {

    private static final int WIN_HEIGHT = 505;
    private static final int WIN_WIDTH = 350;
    private static final int WIN_POSX = 800;
    private static final int WIN_POSY = 100;

    private JTextArea chatAll;
    private JButton btnStart, btnStop;
    private Server server;


    public ServerGUI() {
        server = new Server();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnectAll();
                server.clearHistory();
                System.exit(0);
            }
        });
        setLocation(WIN_POSX, WIN_POSY);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setTitle("Chat Server");
        setResizable(true);

        add(servicePanel(), BorderLayout.NORTH);
        add(windowChatAll(), BorderLayout.CENTER); //с полосой прокрутки

        setVisible(true);
    }

    /**
     * верхняя панель с кнопками
     *
     * @return
     */
    private JPanel servicePanel() {
        JPanel servicePanel = new JPanel(new GridLayout(1, 2));
        btnStop = new JButton("Stop");
        btnStop.setEnabled(false);
        btnStop.addActionListener(e -> {
            disconnectAll();
            server.stopServer();
            setTitle("Chat Server (disabled)");
            chatAll.append("server STOP\n");
            btnStop.setEnabled(false);
            btnStart.setEnabled(true);
        });

        btnStart = new JButton("Start");
        btnStart.addActionListener(e -> {
            server.startServer();
            setTitle("Chat Server (running)");
            chatAll.append("server START\n");
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        });

        servicePanel.add(btnStart);
        servicePanel.add(btnStop);
        return servicePanel;
    }

    private JScrollPane windowChatAll() {
        chatAll = new JTextArea();
        chatAll.setEditable(false);
        return new JScrollPane(chatAll);
    }

    public boolean getWorking() {
        return server.isServerWorking();
    }

    /**
     * подключение клиента
     *
     * @param client
     */
    public void connectClient(ClientView client) {
        server.connectClient(client);
        messageFromClient(client.nameClient() + ": подключился к чату");

    }

    /**
     * обработка сообщения от клиента (рассылка его всем клиентам и добавление в общий чат
     *
     * @param message текст сообщения
     */
    public void messageFromClient(String message) {
        chatAll.append(message + "\n");
        server.sendMessageFromClient(message);

    }

    /**
     * сервер может отправить сообщение отдельному клиенту
     *
     * @param message текст сообщения
     */
    public void answerMessageClient(ClientView client, String message) {
        server.answerMessageClient(client, message);
        chatAll.append(client.nameClient() + ": " + message + "\n");
    }

    /**
     * отключение о сервера одного клиента
     *
     * @param client
     */
    public void disconnectClient(ClientView client) {
        server.disconnectClient(client);
        chatAll.append(client.nameClient() + " отключен от сервера");
    }

    /**
     * отключение всех клиентов
     */
    private void disconnectAll() {
        server.disconnectAll();
        chatAll.append("Все клиенты отключены от сервера\n");
    }


}
