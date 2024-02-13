package FominaKat.Chat.Server;

import FominaKat.Chat.Client.ClientView;

public interface ServerView {
    /**
     * включение сервера
     * @param clientView
     */
    void connectClient(ClientView clientView);

    /**
     * ответ клиенту от сервера
     * @param message
     */
    void answerMessageClient(ClientView clientView, String message);

    /**
     *  сообщение от клиента серверу
     * @param message
     */
    void messageFromClient(String message);

    /**
     * остановка работы сервера
     * @param clientView
     */
    void disconnectClient(ClientView clientView);
}
