package FominaKat;

import FominaKat.Chat.Client.ClientGUI;
import FominaKat.Chat.Server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI chatServer = new ServerGUI();
        ClientGUI petr = new ClientGUI("Petr", chatServer);
        ClientGUI bob = new ClientGUI("Bob Cat", chatServer);

    }
}