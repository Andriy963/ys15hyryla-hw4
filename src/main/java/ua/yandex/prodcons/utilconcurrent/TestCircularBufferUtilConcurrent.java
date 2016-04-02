package ua.yandex.prodcons.utilconcurrent;

import ua.yandex.prodcons.Consumer;
import ua.yandex.prodcons.Producer;

/**
 *
 * @author Andrii Hyryla
 */
public class TestCircularBufferUtilConcurrent {
    public static void main(String[] args) {
        int n = 10;
        ua.yandex.prodcons.threads.CircularBufferThreads circBuf = new ua.yandex.prodcons.threads.CircularBufferThreads(n);
        long begin = System.currentTimeMillis();
        Thread prod1 = new Thread(new Producer(circBuf));
        Thread prod2 = new Thread(new Producer(circBuf));
        Thread prod3 = new Thread(new Producer(circBuf));
        Thread prod4 = new Thread(new Producer(circBuf));
        Thread prod5 = new Thread(new Producer(circBuf));
        Thread cons1 = new Thread(new Consumer(circBuf));
        Thread cons2 = new Thread(new Consumer(circBuf));
        Thread cons3 = new Thread(new Consumer(circBuf));
        prod1.start();
        prod2.start();
        prod3.start();
        prod4.start();
        prod5.start();
        cons1.start();
        cons2.start();
        cons3.start();
        try {
            cons1.join();
            cons2.join();
            cons3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
