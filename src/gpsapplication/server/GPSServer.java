package gpsapplication.server;

import gpsapplication.common.Config;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 *
 * @author dasun
 */



public class GPSServer {

    public static void main(String[] args) {
        System.out.println("Starting GPS Server...");
        try (ServerSocket serverSocket = new ServerSocket(Config.SERVER_PORT)) {
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            System.out.println("Server is listening on port " + Config.SERVER_PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected: " + socket.getInetAddress());
                threadPool.submit(new ClientHandler(socket));
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    
                    GPSDataStorage.saveData(line);
                }
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            }
        }
    }
}
