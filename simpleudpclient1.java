
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

class UDPClient {
    public static void main(String[] args) throws IOException {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = "How many devices are connected?";
        sendData = sentence.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String response = new String(receivePacket.getData()).trim();
        System.out.println("FROM SERVER: " + response);

        clientSocket.close();
    }
}
/*
Output:- 

 F:\networking>java UDPClient
FROM SERVER: Currently connected devices: 0
 */
