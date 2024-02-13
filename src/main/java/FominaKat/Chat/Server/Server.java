package FominaKat.Chat.Server;

import FominaKat.Chat.Client.ClientView;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * обработка сервером данных
 */
public class Server {
    private static boolean serverWorking;
    private ArrayList<ClientView> listConnect;
    private ServerGUI serverGUI;
    private String archiveHistory = "history.txt";
    // private String history = "";

    public Server(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
        this.listConnect = new ArrayList<>();
    }

    public static boolean isServerWorking() {
        return serverWorking;
    }

    public static void startServer() {
        if (!serverWorking) {
            serverWorking = true;
        }
    }

    public static void stopServer() {
        if (serverWorking) {
            serverWorking = false;
        }
    }

    public void connectClient(ClientView client) {
        if (!listConnect.contains(client)) {
            listConnect.add(client);
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
    }

    public void disconnectAll() {
        listConnect.stream().forEach(client -> client.disconnected());
        listConnect.clear();
    }

    private void saveMessageInHistory(String message) {
        try (FileWriter writer = new FileWriter(archiveHistory, true)) {

            writer.write(message + "\n");
        } catch (IOException e) {
            e.getMessage();
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
        }
        return historyBilder.toString();
    }

    public void clearHistory() {

        File file = new File(archiveHistory);
        file.delete();


    }
}
