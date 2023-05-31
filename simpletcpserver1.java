/*
Name       :- Chavda Rohit 
Roll no    :- 04
Course     :- Mca 2
Subject    :- Networking
Assignment :- assignment 2
2) Write the Tcp program in java which checks the String is Anagram or not on the server side 
and give respond to the client
*/
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

class AnagramServer {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                Thread thread = new Thread(() -> {
                    try {
                        InputStream inputStream = clientSocket.getInputStream();
                        OutputStream outputStream = clientSocket.getOutputStream();

                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            String message = new String(buffer, 0, bytesRead);
                            System.out.println("Received message from client: " + message);

                            String[] messageParts = message.split(":");
                            String string1 = messageParts[0];
                            String string2 = messageParts[1];

                            boolean isAnagram = checkAnagram(string1, string2);

                            String response = "The strings " + (isAnagram ? "are" : "are not") + " anagrams of each other.";
                            outputStream.write(response.getBytes());
                        }

                        clientSocket.close();
                        System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkAnagram(String string1, String string2) {
        char[] chars1 = string1.toCharArray();
        char[] chars2 = string2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        return Arrays.equals(chars1, chars2);
    }
}
/*
Output:- 

 Server started on port 1234
Client connected: 127.0.0.1
Received message from client: army:mary
 */
