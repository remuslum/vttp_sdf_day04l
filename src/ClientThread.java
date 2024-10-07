package src;

import java.security.SecureRandom;

public class ClientThread implements Runnable {
    private String message;

    public ClientThread(String msg) {
        message = msg;
    }

    @Override
    public void run() {
        // Thread's body
        // Thread Name
        String threadName = Thread.currentThread().getName();

        // Force it to sleep
        int sleepTime = (new SecureRandom()).nextInt(5) + 3;
        // try {
        //     Thread.sleep(sleepTime + 1000);
        // } catch (InterruptedException e) {
            
        // }
        System.out.printf("Thread[%s] %d message : %s\n", threadName,sleepTime, message);
    }
}
