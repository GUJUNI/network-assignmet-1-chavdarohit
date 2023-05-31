/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- Assignment 2
6) Write the Multithreaded UDP program in java where clients ask the server that how
many client are logged in to that server at current time*/
import java.io.IOException;
import java.net.*;

class mludpserver2 {
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
            System.out.println("Server is running. Listening for client requests...");

            while (true) {
                byte[] receiveData = new byte[BUFFER_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received request from client " + clientAddress.getHostAddress() + ": " + clientRequest);

                // Start a new thread to handle the client request and send a response
                Thread responseThread = new Thread(() -> sendResponse(clientRequest, clientAddress, clientPort, serverSocket));
                responseThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendResponse(String request, InetAddress clientAddress, int clientPort, DatagramSocket serverSocket) {
        try {
            if (request.equals("Client count")) {
                int clientCount = Thread.activeCount() - 2; // Subtracting main thread and server thread

                String response = String.valueOf(clientCount);
                byte[] sendData = response.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// OUTPUT:-
// Server is running. Listening for client requests...
// Received request from client 127.0.0.1: Client count