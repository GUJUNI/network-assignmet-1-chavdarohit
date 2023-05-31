
/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- assignment 2
3) Write a UDP program in which the client ask server that how many devices is connected to that server
and then server give the response on that
 */
import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        int connectedDevices = 0;

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData()).trim();

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            if (sentence.equals("How many devices are connected?")) {
                String response = "Currently connected devices: " + connectedDevices;
                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } else {
                connectedDevices++;
                System.out.println("New device connected: " + IPAddress);
            }
        }
    }
}
