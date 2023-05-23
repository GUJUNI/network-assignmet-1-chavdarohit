/* 
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Operating system
Assignment :- assignment 2
2) Write the Tcp program in java which checks the String is Anagram or not on the server side 
and give respond to the client
*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

class AnagramClient {
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
                System.out.print("Enter two strings to check if they are anagrams of each other (in the format string1:string2): ");
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
Enter two strings to check if they are anagrams of each other (in the format string1:string2): army:mary
Received response from server: The strings are anagrams of each other.
 */