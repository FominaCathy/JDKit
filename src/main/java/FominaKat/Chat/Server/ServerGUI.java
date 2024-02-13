package FominaKat.Chat.Server;

import FominaKat.Chat.Client.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerGUI extends JFrame implements ServerView {

    private static JTextArea chatAll;
    private static final int WIN_HEIGHT = 505;
    private static final int WIN_WIDTH = 350;
    private static final int WIN_POSX = 800;
    private static final int WIN_POSY = 100;
    JButton btnStart = new JButton("Start");
    JButton btnStop = new JButton("Stop");
    private Server server;


    public ServerGUI() {
        server = new Server(this);
        //настройки окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                server.clearHistory();
                System.exit(0);
            }
        });
        setLocation(WIN_POSX, WIN_POSY);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setTitle("Chat Server");
        setResizable(true);

        add(createServicePanel(), BorderLayout.NORTH);

        chatAll = new JTextArea();
        chatAll.setEditable(false);
        add(new JScrollPane(chatAll), BorderLayout.CENTER); //с полосой прокрутки

        setVisible(true);
    }

    /**
     * верхняя панель с кнопками
     *
     * @return
     */
    private JPanel createServicePanel() {
        // панель с кнопками
        JPanel servicePanel = new JPanel(new GridLayout(1, 2));
        btnStop.setEnabled(false);
        servicePanel.add(btnStart);
        servicePanel.add(btnStop);
        btnStart.addActionListener(e -> {
            Server.startServer();
            chatAll.append("server START\n");
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        });

        btnStop.addActionListener(e -> {
            disconnectAll();
            Server.stopServer();
            chatAll.append("server STOP\n");
            btnStop.setEnabled(false);
            btnStart.setEnabled(true);
        });
        return servicePanel;
    }

    public static boolean getWorking() {
        return Server.isServerWorking();
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
     * @param message
     */
    public void messageFromClient(String message) {
        chatAll.append(message + "\n");
        server.sendMessageFromClient(message);

    }


    /**
     * сервер может отправить сообщение отдельному клиенту
     *
     * @param message
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
