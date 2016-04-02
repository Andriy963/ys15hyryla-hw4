package ua.yandex.prodcons;

import java.util.Random;
import ua.yandex.prodcons.threads.CircularBufferThreads;

/**
 *
 * @author Andrii Hyryla
 */
public class Producer implements Runnable{

    private final CircularBufferThreads circBuf;

    public Producer(CircularBufferThreads circBuf) {
        this.circBuf = circBuf;
    }
    
    @Override
    public void run() {
        String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
        };
        Random random = new Random();

        for (String importantInfo1 : importantInfo) {
            circBuf.put(importantInfo1);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        circBuf.put("DONE");
    }
    
}
