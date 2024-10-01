# Chat Server Application

This is a simple multi-user chat server application implemented in Java. It allows users to connect, chat with each other, and manage their nicknames. The server supports basic features like broadcasting messages, private messaging, and user management through an admin interface.

## Features

- **User Authentication**: Users must enter a password to connect to the server. This ensures only authorized users can access the chat.

- **Nickname Management**: Users can set their nickname when they connect and can change it at any time. If a nickname is already in use, users will be prompted to choose another one.

- **Broadcast Messaging**: All connected users receive messages broadcasted by others. This feature allows for open communication among users.

- **Private Messaging**: Users can send messages privately to other users using the `/whisper <nickname> <message>` command. This allows for one-on-one conversations.

- **Chat History**: The server keeps a history of all messages sent in the chat. Users can see messages sent even after they reconnect.

- **Admin Controls**: An admin can ban users and shut down the server. This provides moderation capabilities to maintain a healthy chat environment.

- **Active User List**: The server broadcasts a list of currently active users to all connected clients, helping users to see who else is online.

## File Structure

The application consists of the following files:

- **Server.java**: 
  - This file contains the main `Server` class, which manages the socket server, handles incoming connections, and coordinates message broadcasting among connected clients.
  - Key methods include:
    - `start()`: Starts listening for client connections.
    - `broadcast(String msg)`: Sends a message to all connected clients.
    - `shutdown()`: Cleans up resources and closes connections gracefully.

- **ChatServer.java**: 
  - This is the entry point of the application. It prompts for a passcode to start the server and creates an instance of the `Server` class.
  - Key method:
    - `main(String[] args)`: Reads the passcode input and starts the server.

- **ConnectionHandler.java**: 
  - This class manages individual client connections. It handles user authentication, nickname management, and message processing.
  - Key methods include:
    - `initialize()`: Prompts the user for a password and nickname.
    - `run()`: Listens for incoming messages from the connected client.
    - `processMessage(String message)`: Processes user commands and messages.
    - `sendMessage(String message)`: Sends a message to the connected client.

- **Client.java**: 
  - This file contains the `Client` class, which provides a graphical user interface (GUI) for users to connect to the chat server, send messages, and view chat history.
  - Key methods include:
    - `createUI()`: Initializes and sets up the user interface.
    - `run()`: Establishes a connection to the server and listens for incoming messages.
    - `sendMessage()`: Sends user input to the server.

- **Admin.java**: 
  - This class provides an admin interface to manage the chat server. It runs on a separate thread and allows the admin to ban users and shut down the server.
  - Key methods include:
    - `run()`: Listens for admin commands.
    - `banUser(String nickname)`: Bans a user from the chat.

## Requirements

- Java Development Kit (JDK) 8 or higher.
- A terminal or command prompt to run the server and clients.

## How to Run

### Starting the Server

1. Compile the server classes:
   ```bash
   javac Server.java ChatServer.java ConnectionHandler.java Admin.java
