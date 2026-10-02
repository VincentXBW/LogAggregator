import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

public class Main{
    public static void main(String[] args){
        int port = 5000; // states port, may implement a port choosing system

        System.out.println("Server is booting");

        try(ServerSocket serverSocket = new ServerSocket(port)){ // create server socket
            System.out.println("Server is listening on port " + port);

            while(true) { // accepts clients
                System.out.println("Waiting for client connection");
                try(Socket clientSocket = serverSocket.accept()){ // blocks execution until client connects
                    System.out.println("Client connected from: " + clientSocket.getRemoteSocketAddress());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // input and output streams
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                    String clientMessage = reader.readLine(); // read data from client
                    if(clientMessage != null){
                        System.out.println("Received: " + clientMessage);

                        writer.println("Echo from server: " + clientMessage); // send back to server
                    }
                } catch(IOException e){
                    System.err.println("Exception handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port + ". It may be in use.");
            e.printStackTrace();
        }
    }
}