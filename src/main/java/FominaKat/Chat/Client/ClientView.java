package FominaKat.Chat.Client;

public interface ClientView {

    String nameClient();

    /**
     * подключиться к серверу
     */
    void connected();

    /**
     * отправить сообщение серверу
     *
     * @param message сообщение серверу
     */
    void sendMessageServer(String message);

    /**
     * получить ответ от сервара
     *
     * @param message
     */
    void answerMessageFromSever(String message);

    /**
     * отсоединиться
     */
    void disconnected();


}
