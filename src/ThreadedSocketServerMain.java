package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedSocketServerMain {
    public static void main(String[] args) throws IOException{
        // Default port number
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.printf(">> Listening on port %d\n", port);

        String name = Thread.currentThread().getName();

        // Create a thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(3);

        // Create a server
        ServerSocket server = new ServerSocket(port);

        int connections = 0;

        while (true) {
            System.out.printf("[%s] %d Waiting for connection\n", name, connections);
            connections++;

            // Wait for incoming connection, block
            Socket conn = server.accept();

            //Create a connection handler with the client socket
            ConnectionHandler handler = new ConnectionHandler(conn);

            // Pass the handler (work) to the thread pool
            thrPool.submit(handler);

            System.out.printf("[%s] Submitted connection handler to thread pool\n", name);
        }

    }
}
