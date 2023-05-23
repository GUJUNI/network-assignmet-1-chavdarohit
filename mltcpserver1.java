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
import java.net.ServerSocket;
import java.net.Socket;

class Server {
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
                            String substring = messageParts[0];
                            String inputString = messageParts[1];

                            int count = countSubstring(inputString, substring);

                            String response = "Number of occurrences of '" + substring + "' in message: " + count;
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

    private static int countSubstring(String string, String substring) {
        int count = 0;
        int index = 0;

        while ((index = string.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count;
    }
}
/*
Output:- 

 Server started on port 1234
 Client connected: 127.0.0.1
 Received message from client: rohit:this is rohit, rohit is a good boy
 */