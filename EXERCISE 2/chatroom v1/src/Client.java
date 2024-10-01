import java.io.*;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private String nickname;
    private String ipvalue;

    // UI Components
    private JFrame frame;
    private JTextArea chatArea;
    private JTextArea userArea; // Area for displaying active users
    private JTextField inputField;
    private JButton sendButton;
    private JButton quitButton;
    private JTextField nicknameField;
    private JTextField ipField;

    @Override
    public void run() {
        try {
            client = new Socket(ipvalue, 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Start a thread to handle incoming messages
            new Thread(new InputHandler()).start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                if (inMessage.startsWith("Active users:")) {
                    userArea.setText(inMessage); // Update the user area with active users
                } else {
                    chatArea.append(inMessage + "\n");
                }
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    // Initialize the UI components
    public void createUI() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        userArea = new JTextArea();
        userArea.setEditable(false); // Users can't edit active users
        JScrollPane userScrollPane = new JScrollPane(userArea);
        userScrollPane.setPreferredSize(new Dimension(150, 0)); // Set fixed width for user area

        inputField = new JTextField();
        sendButton = new JButton("Send");
        quitButton = new JButton("Quit");
        nicknameField = new JTextField();
        ipField = new JTextField();

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Nickname entry panel
        JPanel nicknamePanel = new JPanel(new BorderLayout());
        nicknamePanel.add(new JLabel("Enter your nickname:"), BorderLayout.WEST);
        nicknamePanel.add(nicknameField, BorderLayout.CENTER);

        // IP entry panel
        JPanel ipPanel = new JPanel(new BorderLayout());
        ipPanel.add(new JLabel("Enter your IP:"), BorderLayout.WEST);
        ipPanel.add(ipField, BorderLayout.CENTER);

        // Send button action listener
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Quit button action listener
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (out != null) {
                    out.println("/quit");
                }
                shutdown();
            }
        });

        // Confirm nickname button
        JButton confirmButton = new JButton("Confirm Nickname");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredNickname = nicknameField.getText().trim();
                if (enteredNickname.equalsIgnoreCase("admin")) {
                    JOptionPane.showMessageDialog(frame, "The nickname 'admin' is not allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    nickname = enteredNickname;
                    out.println(nickname);
                    nicknameField.setEnabled(false);
                    confirmButton.setEnabled(false);
                }
            }
        });
        nicknamePanel.add(confirmButton, BorderLayout.EAST);

        // Action to set IP value and run the client
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipvalue = ipField.getText();
                new Thread(Client.this).start();
            }
        });
        ipPanel.add(connectButton, BorderLayout.EAST);

        // Adding components to the frame
        frame.add(ipPanel, BorderLayout.NORTH);
        frame.add(nicknamePanel, BorderLayout.CENTER);
        frame.add(userScrollPane, BorderLayout.WEST); // User area on the left
        frame.add(chatScrollPane, BorderLayout.CENTER); // Chat area in the center
        frame.add(inputPanel, BorderLayout.SOUTH); // Input panel at the bottom

        frame.setVisible(true);
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (out != null && !message.isEmpty()) {
            out.println(message);
            inputField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Message cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Cleanly shut down the client
    public void shutdown() {
        done = true;
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null && !client.isClosed()) client.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    class InputHandler implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while (!done) {
                    if ((message = in.readLine()) != null) {
                        if (message.startsWith("Active users:")) {
                            userArea.setText(message); // Update the user area with active users
                        } else if (message.equals("/quit")) {
                            shutdown();
                        } else {
                            chatArea.append(message + "\n");
                        }
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client client = new Client();
            client.createUI();
        });
    }
}

