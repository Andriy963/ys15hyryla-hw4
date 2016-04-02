package ua.yandex.prodcons;

import java.util.Random;
import ua.yandex.prodcons.threads.CircularBufferThreads;

/**
 *
 * @author Andrii Hyryla
 */
public class Consumer implements Runnable{

    private final CircularBufferThreads circBuf;

    public Consumer(CircularBufferThreads circBuf) {
        this.circBuf = circBuf;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        for (String message = circBuf.get(); 
                ! message.equals("DONE"); message = circBuf.get()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        
    }
    
}
