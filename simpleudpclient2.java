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
import java.util.Scanner;

public class simpleudpclient2 {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverIP = InetAddress.getByName("localhost");
            int serverPort = 9876;

            byte[] sendData;
            byte[] receiveData = new byte[1024];

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter domain name to resolve: ");
            String domainName = scanner.nextLine();

            sendData = domainName.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String ipAddress = new String(receivePacket.getData()).trim();
            System.out.println("IP address resolved: " + ipAddress);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
 OUTPUT:-
Enter domain name to resolve: www.google.com
IP address resolved: 142.250.192.68
 */