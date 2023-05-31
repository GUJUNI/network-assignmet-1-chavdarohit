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

class mludpclient2 {
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            String request = "Client count";
            byte[] sendData = request.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[BUFFER_SIZE];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Number of clients logged in: " + serverResponse);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// OUTPUT:-
// Number of clients logged in: 0