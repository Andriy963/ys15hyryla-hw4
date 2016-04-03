package ua.yandex.lockfree;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Andrii Hyryla
 */
public class LockFreeTest {

    private static class LockFreeThread implements Runnable {

        BigInteger result;
        private final LockFree lockfree;

        public LockFreeThread(LockFree lockfree) {
            this.lockfree = lockfree;
        }

        @Override
        public void run() {
            result = lockfree.next();
        }

        public BigInteger getResult() {
            return result;
        }
    }

    @Test
    public void nextTestWithTenThreads() {
        int quantityOfThreads = 10;
        Thread[] th = new Thread[quantityOfThreads];
        LockFreeThread[] lfth = new LockFreeThread[quantityOfThreads];

        LockFree lockfree = new LockFree();
        for (int i = 0; i < quantityOfThreads; i++) {
            lfth[i] = new LockFreeThread(lockfree);
            th[i] = new Thread(lfth[i]);
        }

        Set<BigInteger> set = new HashSet<>();

        for (int i = 0; i < quantityOfThreads; i++) {
            th[i].start();

        }
        for (int i = 0; i < quantityOfThreads; i++) {
            try {
                th[i].join();
            } catch (InterruptedException ex) {
            }
        }
        for (int i = 0; i < quantityOfThreads; i++) {
            set.add(lfth[i].getResult());
        }

        assertEquals(quantityOfThreads, set.size());
    }
    
    @Test
    public void nextTestWithHundredThreads() {
        int quantityOfThreads = 100;
        Thread[] th = new Thread[quantityOfThreads];
        LockFreeThread[] lfth = new LockFreeThread[quantityOfThreads];

        LockFree lockfree = new LockFree();
        for (int i = 0; i < quantityOfThreads; i++) {
            lfth[i] = new LockFreeThread(lockfree);
            th[i] = new Thread(lfth[i]);
        }

        Set<BigInteger> set = new HashSet<>();

        for (int i = 0; i < quantityOfThreads; i++) {
            th[i].start();

        }
        for (int i = 0; i < quantityOfThreads; i++) {
            try {
                th[i].join();
            } catch (InterruptedException ex) {
            }
        }
        for (int i = 0; i < quantityOfThreads; i++) {
            set.add(lfth[i].getResult());
        }

        assertEquals(quantityOfThreads, set.size());
    }
    
    @Test
    public void nextTestWithThousandThreads() {
        int quantityOfThreads = 1000;
        Thread[] th = new Thread[quantityOfThreads];
        LockFreeThread[] lfth = new LockFreeThread[quantityOfThreads];

        LockFree lockfree = new LockFree();
        for (int i = 0; i < quantityOfThreads; i++) {
            lfth[i] = new LockFreeThread(lockfree);
            th[i] = new Thread(lfth[i]);
        }

        Set<BigInteger> set = new HashSet<>();

        for (int i = 0; i < quantityOfThreads; i++) {
            th[i].start();

        }
        for (int i = 0; i < quantityOfThreads; i++) {
            try {
                th[i].join();
            } catch (InterruptedException ex) {
            }
        }
        for (int i = 0; i < quantityOfThreads; i++) {
            set.add(lfth[i].getResult());
        }

        assertEquals(quantityOfThreads, set.size());
    }
}
