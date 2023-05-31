/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- Assignment 2
8) Write the Multithreaded TCP program in java 
where the client can search for an element in an array on the server-side*/
import java.io.*;
import java.net.Socket;

class mltcpclient2 {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            System.out.print("Enter the element to search in the array: ");
            String searchValue = reader.readLine();
            writer.println(searchValue);

            BufferedReader responseReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = responseReader.readLine();
            System.out.println("Server response: " + response);

            socket.close();
            System.out.println("Disconnected from server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
 OUTPUT:-
Connected to server.
Enter the element to search in the array: 7
Server response: Element found in the array.
Disconnected from server.
 */