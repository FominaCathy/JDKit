package FominaKat.Chat.Server;

import FominaKat.Chat.Client.ClientView;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * обработка сервером данных
 */
public class Server {
    private static boolean serverWorking;
    private ArrayList<ClientView> listConnect;

    private String archiveHistory = "history.txt";
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server() {
        this.listConnect = new ArrayList<>();
    }

    public static boolean isServerWorking() {
        return serverWorking;
    }

    public static void startServer() {
        if (!serverWorking) {
            serverWorking = true;
            logger.info("Сервер запущен");
        }
    }

    public static void stopServer() {
        if (serverWorking) {
            serverWorking = false;
            logger.info("Сервер остановлен");
        }
    }

    public void connectClient(ClientView client) {
        if (!listConnect.contains(client)) {
            listConnect.add(client);
            logger.info(String.format("клиент '%s' - подключен", client.nameClient()));
        }
        String history = exactMessageHistory();
        client.answerMessageFromSever(history);
    }

    /**
     * отправка сообщения всем подключенным клиентам
     *
     * @param message
     */
    public void sendMessageFromClient(String message) {

        listConnect.stream().forEach(client -> client.answerMessageFromSever(message + "\n"));
        saveMessageInHistory(message);
    }

    /**
     * сервер может отправить сообщение отдельному клиенту
     *
     * @param client
     * @param message
     */

    public void answerMessageClient(ClientView client, String message) {
        client.answerMessageFromSever(message);
    }

    public void disconnectClient(ClientView client) {
        client.disconnected();
        listConnect.remove(client);
        logger.info(String.format("клиент '%s' - отключен", client.nameClient()));
    }

    public void disconnectAll() {
        listConnect.stream().forEach(client -> client.disconnected());
        listConnect.clear();
        logger.info("все пользователи отключены");
    }

    private void saveMessageInHistory(String message) {
        try (FileWriter writer = new FileWriter(archiveHistory, true)) {

            writer.write(message + "\n");
        } catch (IOException e) {
            e.getMessage();
            logger.error("не удалось сохранить сообщение в историю: " + e.getMessage());
        }
    }

    private String exactMessageHistory() {
        StringBuilder historyBilder = new StringBuilder();
        try (FileReader reader = new FileReader(archiveHistory)) {
            int c;
            while ((c = reader.read()) != -1) {
                historyBilder.append((char) c);
            }
        } catch (IOException e) {
            e.getMessage();
            logger.error("не удалось восстановить историю сообщений: " + e.getMessage());
        }
        return historyBilder.toString();
    }

    public void clearHistory() {

        File file = new File(archiveHistory);
        file.delete();
        logger.info("файл history - удален");

    }
}
