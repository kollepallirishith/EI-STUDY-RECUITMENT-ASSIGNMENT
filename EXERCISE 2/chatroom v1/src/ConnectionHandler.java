import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String nickname;
    private Server server;

    public ConnectionHandler(Socket client, Server server) {
        this.client = client;
        this.server = server;
        initialize();
    }

    private void initialize() {
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Prompt for password
            out.println("Please enter the password:");
            String inputPass = in.readLine();

            // Check password
            if (inputPass.equals(server.getPass())) {
                out.println("Authenticated! You can now set your nickname.");
                Thread.sleep(100);
                // Prompt for nickname
                out.println("Enter your nickname:");
                nickname = in.readLine().trim(); // Trim to avoid extra spaces

                // Check if nickname is valid
                while (server.getConnectionHandler(nickname) != null || nickname.isEmpty()) {
                    out.print("This nickname is already in use or invalid. Try another one: ");
                    nickname = in.readLine().trim(); // Trim to avoid extra spaces
                }

                // Add the nickname and notify other users
                server.addNickname(nickname, this);
                System.out.println(nickname + " connected");
                server.sendChatHistory(this);
                server.broadcast(nickname + " joined the chat"); // Broadcast user joined message
                updateActiveUsers();
            } else {
                out.println("Authentication failed. Closing connection.");
                shutdown();
            }

        } catch (IOException e) {
            shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                processMessage(message);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    private void processMessage(String message) {
        if (message.startsWith("/nick ")) {
            changeNickname(message);
        } else if (message.startsWith("/quit")) {
            handleQuit(); // Notify other users
            shutdown();
        } else if (message.startsWith("/whisper ")) {
            sendPrivateMessage(message);
        } else {
            server.broadcast(nickname + ": " + message); // Format the message properly
        }
    }

    private void handleQuit() {
        server.broadcast(nickname + " has left the chat."); // Notify others
        System.out.println(nickname + " has left the chat.");
        server.removeNickname(nickname); // Remove from the nickname map
        updateActiveUsers(); // Update active users list
    }

    private void changeNickname(String message) {
        String newNickname = message.split(" ", 2)[1].trim(); // Trim to avoid extra spaces
        if (server.getConnectionHandler(newNickname) == null && !newNickname.isEmpty()) {
            server.broadcast(nickname + " renamed to " + newNickname);
            server.removeNickname(nickname);
            nickname = newNickname;
            server.addNickname(nickname, this);
            out.println("Successfully changed nickname to " + nickname);
            updateActiveUsers();
        } else {
            out.println("Nickname already in use or invalid.");
        }
    }

    private void sendPrivateMessage(String message) {
        String[] parts = message.split(" ", 3);
        if (parts.length == 3) {
            String recipientNickname = parts[1].trim(); // Trim to avoid extra spaces
            String privateMessage = parts[2];
            ConnectionHandler recipient = server.getConnectionHandler(recipientNickname);
            if (recipient != null) {
                recipient.sendMessage("[Private] " + nickname + ": " + privateMessage);
                out.println("[Private] " + nickname + ": " + privateMessage);
            } else {
                out.println("User " + recipientNickname + " not found.");
            }
        } else {
            out.println("Usage: /whisper <username> <message>");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    public void shutdown() {
        try {
            in.close();
            out.close();
            if (!client.isClosed()) {
                client.close();
            }
        } catch (IOException e) {
            // Ignore
        }
    }

    private void updateActiveUsers() {
        StringBuilder activeUsers = new StringBuilder("Active users: ");
        for (String activeNickname : server.getActiveUserList()) {
            activeUsers.append(activeNickname).append(", ");
        }
        String userList = activeUsers.length() > 0 ? activeUsers.substring(0, activeUsers.length() - 2) : "No active users.";
        server.broadcast(userList);
    }
}
