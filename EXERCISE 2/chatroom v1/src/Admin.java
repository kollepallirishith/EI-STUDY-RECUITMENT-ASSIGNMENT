import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin implements Runnable {
    private Server server;

    public Admin(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        BufferedReader commandInput = new BufferedReader(new InputStreamReader(System.in));
        String command;

        try {
            while (true) {
                System.out.print("Admin> ");
                command = commandInput.readLine();
                if (command.startsWith("/ban ")) {
                    banUser(command.split(" ", 2)[1]);
                } else if (command.equals("/quit")) {
                    server.shutdown();
                    break;
                } else {
                    System.out.println("Unknown command. Use /ban <username> or /quit.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void banUser(String nickname) {
        ConnectionHandler handler = server.getConnectionHandler(nickname);
        if (handler != null) {
            handler.sendMessage("You have been banned from the chat.");
            handler.shutdown();
            server.broadcast(nickname + " has been banned from the chat.");
            System.out.println("User " + nickname + " has been banned.");
        } else {
            System.out.println("User " + nickname + " not found.");
        }
    }
}
