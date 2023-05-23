
/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Operating system
Assignment :- assignment 2

4) Write a UDP program in which the client will ask the server about thier storage size and server
 will respond and give the size of that
 */
import java.io.*;
import java.net.*;

class stoServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData()).trim();

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            if (sentence.equals("Tell me your storage size")) {
                String response = "My storage size is 1 TB";
                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } else {
                System.out.println("Unknown request from client: " + sentence);
            }
        }
    }
}
