package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.Reader;
import java.io.Writer;

public class ConnectionHandler implements Runnable {
    private final Socket clientConn;

    public ConnectionHandler(Socket conn) {
        this.clientConn = conn;
    }

    @Override
    public void run() {
        
        String name = Thread.currentThread().getName();

        try {
            // Get input stream
            InputStream is = clientConn.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            //Get output stream
            OutputStream os = clientConn.getOutputStream();
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            String msg = br.readLine();
            System.out.printf("[%s]>>> Msg from Client: %s\n", name, msg);

            msg = msg.toUpperCase();

            bw.write(msg);
            bw.flush();

            clientConn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
