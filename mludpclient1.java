
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

class stoClient {
    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = "Tell me your storage size";
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData()).trim();
        System.out.println("Server storage size: " + response);

        clientSocket.close();
    }
}
/*
Output:-

 F:\networking>java stoClient
Server storage size: My storage size is 1 TB
 */
