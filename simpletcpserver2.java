/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- Assignment 2
7) Write the Simple TCP program in java where clients ask the server about the configuration
done in the server and server respond to it*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
 class simpletcpserver2 {
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running. Listening for client requests...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread clientThread = new Thread(() -> handleClientRequest(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClientRequest(Socket clientSocket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read client request
            String clientRequest = reader.readLine();
            System.out.println("Received request from client: " + clientRequest);

            // Process client request and generate server configuration details
            String serverConfiguration = getServerConfiguration();

            // Send server configuration response to the client
            writer.println(serverConfiguration);

            // Close the socket
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getServerConfiguration() {
        // Generate and return the server configuration details
        String configuration = "Server Configuration:\n";
        configuration += "Operating System: Windows\n";
        configuration += "Memory: 8GB\n";
        configuration += "Processor: Intel Core i5\n";
        return configuration;
    }
}
