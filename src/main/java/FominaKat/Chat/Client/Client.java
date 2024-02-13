package FominaKat.Chat.Client;

import FominaKat.Chat.Server.Server;
import FominaKat.Chat.Server.ServerGUI;

import javax.swing.*;

public class Client {
    private ClientView clientView;
    private ServerGUI server; //TODO исправить
    private boolean connect;

    public Client(ClientView clientView, ServerGUI server) {
        this.clientView = clientView;
        this.server = server;
    }

    /**
     * отправка сообщение на сервер
     *
     * @param message
     */
    public void sendMessageServer(String message) {
        if (!message.isEmpty() && ServerGUI.getWorking()) {
            String text = clientView.nameClient() + ": " + message;
            server.messageFromClient(text);
        }
    }

    /**
     * получение и проверка сообщений от сервера
     */

    public String checkMessageFromSever(String message) {
        // проверка сообщения
        return message;
    }

    public void connected() {
        if (ServerGUI.getWorking()) {
            server.connectClient(clientView);
            connect = true;
        } else {
            connect = false;
        }
    }

    public void disconnected() {
        if (connect) {
            connect = false;
        }
    }

    public boolean isConnect() {
        return connect;
    }

}

