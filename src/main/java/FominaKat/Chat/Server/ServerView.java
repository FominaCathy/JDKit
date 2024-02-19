package FominaKat.Chat.Server;

import FominaKat.Chat.Client.ClientView;

public interface ServerView {
    /**
     * подключение клиента
     * @param clientView клиент
     */
    void connectClient(ClientView clientView);

    /**
     * ответ клиенту от сервера
     * @param message текст сообщения
     */
    void answerMessageClient(ClientView clientView, String message);

    /**
     *  сообщение от клиента серверу
     * @param message текст сообщения
     */
    void messageFromClient(String message);

    /**
     * остановка работы сервера
     * @param clientView клиент
     */
    void disconnectClient(ClientView clientView);
}
