/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- Assignment 2
5) Write the UDP program in java UDP DNS resolver program in Java where clients can send domain 
name queries to a server using UDP, and the server responds with the corresponding IP address.
*/
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class simpleudpserver2 {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String domainName = new String(receivePacket.getData()).trim();
                System.out.println("Received DNS query from client: " + domainName);

                // Perform DNS resolution using InetAddress
                InetAddress ipAddress = InetAddress.getByName(domainName);

                String ipString = ipAddress.getHostAddress();
                sendData = ipString.getBytes();

                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Sent DNS response to client.");
                receiveData = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
Received DNS query from client: www.google.com
Sent DNS response to client.
*/