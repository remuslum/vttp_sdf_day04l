package src;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServerMain {
    public static void main(String[] args) {
        // Create a fixed thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            System.out.println(">>> MAIN: Creating thread" + i);
            // Create a work for the thread to perform
            ClientThread t = new ClientThread("No " + i);
            // Pass the work to the pool
            thrPool.submit(t);
        }

        System.out.println("\n\n\n==============\nMain thread completed");
    }
}
