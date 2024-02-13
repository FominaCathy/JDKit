package FominaKat.Chat.Client;

import FominaKat.Chat.Server.ServerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame implements ClientView {
    private JTextArea chatlogClient;

    private static final int WIN_HEIGHT = 305;
    private static final int WIN_WIDTH = 350;
    private static final int WIN_POSX = 300;
    private static final int WIN_POSY = 100;
    private ServerGUI server;
    private String name; //TODO урать ???
    private JPanel panelTop;
    private JTextField nameUser;
    Client client;

    JButton sendBtn;

    /**
     * графическое представление окна клиента
     *
     * @param name
     * @param server
     */
    public ClientGUI(String name, ServerGUI server) {
        this.server = server;
        this.name = name;

        client = new Client(this, server);

        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POSX, WIN_POSY);
        setTitle("Chat client");

        add(createPanelTop(), BorderLayout.NORTH); //верхняя панель
        add(new JScrollPane(createChatlog()), BorderLayout.CENTER); //окно чата
        add(createMessagePanel(), BorderLayout.SOUTH); //нижняя панель

        setVisible(true);
    }

    /**
     * отрисовка верхней панели с данными клиента
     *
     * @return
     */
    private JPanel createPanelTop() {
        panelTop = new JPanel(new GridLayout(2, 1));

        JPanel setting = new JPanel(new GridLayout(1, 2));
        JTextField address = new JTextField("127.0.0");
        JTextField port = new JTextField("8889");
        setting.add(address);
        setting.add(port);

        JPanel userData = new JPanel(new GridLayout(1, 3));
        nameUser = new JTextField(name);
        JPasswordField loginUser = new JPasswordField("*****");

        JButton loginBtn = new JButton("login");

        userData.add(nameUser);
        userData.add(loginUser);
        userData.add(loginBtn);

        panelTop.add(setting);
        panelTop.add(userData);

        loginBtn.addActionListener(e -> {
            connected();
        });
        return panelTop;
    }

    /**
     * отрисовка нижней панели для отправки нового сообщения
     *
     * @return
     */
    private JPanel createMessagePanel() {

        JPanel messagePanel = new JPanel(new BorderLayout());
        JTextField messageField = new JTextField();
        sendBtn = new JButton("send");
        sendBtn.setEnabled(false);
        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendBtn, BorderLayout.EAST);
        sendBtn.addActionListener(e -> {
            sendMessageServer(messageField.getText());
            messageField.setText("");
        });
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessageServer(messageField.getText());
                    messageField.setText("");
                }
            }
        });
        return messagePanel;
    }

    /**
     * создание поля чата
     *
     * @return
     */
    private JTextArea createChatlog() {
        chatlogClient = new JTextArea();
        chatlogClient.setEditable(false);
        return chatlogClient;
    }

    @Override
    public void connected() {
        client.connected();
        if (client.isConnect()) {
            sendBtn.setEnabled(true);
            panelTop.setVisible(false);
            setTitle("Chat client " + nameUser.getText());
            //chatlogClient.append("Успешное подключение\n");
        } else {
            chatlogClient.append("Сервер недоступен\n");
            setTitle("Chat client (disconnect)");

        }
    }

    @Override
    public void sendMessageServer(String message) {
        client.sendMessageServer(message);
    }

    public void answerMessageFromSever(String message) {
        //возможна в дальнейшем проверка сообщений от сервера
        String checkMsg = client.checkMessageFromSever(message);
        chatlogClient.append(checkMsg);
    }

    @Override
    public void disconnected() {
        client.disconnected();
        chatlogClient.setText("Сервер недоступен\n");
        panelTop.setVisible(true);
        sendBtn.setEnabled(false);
    }

    public String nameClient() {
        return nameUser.getText();
    }
}
