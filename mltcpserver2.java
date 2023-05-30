/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- Assignment 2
8) Write the Multithreaded TCP program in java 
where the client can search for an element in an array on the server-side*/
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

class mltcpserver2 {
    private static final int PORT = 8888;
    private static int[] array = {1, 4, 2, 7, 5, 9, 3};

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String searchValue = reader.readLine();
                System.out.println("Received search request for element: " + searchValue);

                boolean found = Arrays.stream(array).anyMatch(element -> element == Integer.parseInt(searchValue));
                if (found) {
                    writer.println("Element found in the array.");
                } else {
                    writer.println("Element not found in the array.");
                }

                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
OUTPUT:-
Server started. Listening on port 8888
Client connected: 127.0.0.1
Received search request for element: 6
Client disconnected: 127.0.0.1
 */
