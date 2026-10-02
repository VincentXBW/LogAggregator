import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (
                Socket socket = new Socket(host, port);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner console = new Scanner(System.in)
        ) {

            writer.println("2026-10-02 [INFO] Test log for LogAggregator over TCP");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}