import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ArrayList<ConnectionHandler> connections;
    private ServerSocket serverSocket;
    private boolean isRunning;
    private ExecutorService pool;
    private HashMap<String, ConnectionHandler> nicknameMap;
    private List<String> chatHistory;
    private String pass;
    public Server(int port, String pass) {
        this.pass = pass;
        isRunning = false;
        connections = new ArrayList<>();
        nicknameMap = new HashMap<>();
        chatHistory = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newCachedThreadPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        isRunning = true;
        listenForConnections();
    }

    private void listenForConnections() {
        while (isRunning) {
            try {
                Socket client = serverSocket.accept();
                ConnectionHandler handler = new ConnectionHandler(client, this);
                connections.add(handler);
                pool.execute(handler);
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public void shutdown() {
        isRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String msg) {
        chatHistory.add(msg);
        for (ConnectionHandler ch : connections) {
            ch.sendMessage(msg);
        }
    }

    public void sendChatHistory(ConnectionHandler handler) {
        for (String message : chatHistory) {
            handler.sendMessage(message);
        }
    }

    public void addNickname(String nickname, ConnectionHandler handler) {
        nicknameMap.put(nickname, handler);
    }

    public void removeNickname(String nickname) {
        nicknameMap.remove(nickname);
    }

    public ConnectionHandler getConnectionHandler(String nickname) {
        return nicknameMap.get(nickname);
    }

    public List<String> getActiveUserList() {
        return new ArrayList<>(nicknameMap.keySet());
    }

    public String getPass() {
        return pass;
    }
}
