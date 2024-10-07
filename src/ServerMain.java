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

public class ServerMain {
    public static void main(String[] args) throws IOException{
        // Default port number
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        System.out.printf(">> Listening on port %d\n", port);

        ServerSocket server = new ServerSocket(port);

        System.out.println("Waiting for connection");

        Socket conn = server.accept();

        System.out.println("Got a client connection");

        // Get input stream
        InputStream is = conn.getInputStream();
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);

        OutputStream os = conn.getOutputStream();
        Writer writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);

        String msg = br.readLine();
        System.out.printf(">>> Msg from Client: %s\n", msg);

        msg = msg.toUpperCase();

        bw.write(msg);
        bw.flush();

        conn.close();

    }
}
