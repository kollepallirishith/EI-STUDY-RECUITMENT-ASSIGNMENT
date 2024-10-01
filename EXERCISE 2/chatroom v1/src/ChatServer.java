import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the passcode to start the chat server: ");
        String passcode = scanner.nextLine(); // Get the passcode input

        Server server = new Server(9999, passcode);
        new Thread(new Admin(server)).start(); // Start admin thread
        server.start();
        scanner.close();
    }
}
