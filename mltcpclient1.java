/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Operating system
Assignment :- assignment 2
1) Write a multhreaded TCP client server program that take string from the client and server finds the 
 occurence of that word from the string given by client 
*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server");

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter a message to send to the server (in the format substring:inputString): ");
                String message = scanner.nextLine();

                outputStream.write(message.getBytes());

                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                String response = new String(buffer, 0, bytesRead);
                System.out.println("Received response from server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Output:- 

Connected to server
Enter a message to send to the server (in the format substring:inputString): rohit:this is rohit, rohit is a good boy
Received response from server: Number of occurrences of 'rohit' in message: 2
*/