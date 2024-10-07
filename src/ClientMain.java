package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException{
        
        //Create a socket to connect to the server
        Socket socket = new Socket("localhost", 5000);

        System.out.println(">>>> Connected to server");

        Console cons = System.console();

        // Read a message
        String message = cons.readLine(">>> ");

        // Output stream
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        // Input Stream
        InputStream is = socket.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        // Write the message to ther server
        bw.write(message + "\n");
        bw.flush();

        // Read the result back in
        message = br.readLine();

        System.out.printf("From Server: %s\n", message);

        // Close the connection
        socket.close();
    }
}
