package ua.yandex.prodcons.utilconcurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Andrii Hyryla
 */
public class CircularBufferUtilConcurrent {

    private final String[] buffer;
    private final int bufferSize;
    private int quantityOfElements;
    private int putPosition;
    private int getPosition;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public CircularBufferUtilConcurrent(int bufferSize) {
        this.bufferSize = bufferSize;
        buffer = new String[bufferSize];
        for (int i = 0; i < bufferSize; i++) {
            buffer[i] = new String();
        }
    }

    public void put(String message) throws InterruptedException {
        lock.lock();
        try {
            while (quantityOfElements == bufferSize) {
                try {
                    notFull.await();
                } catch (InterruptedException ex) {
                }
            }
            quantityOfElements++;
            buffer[putPosition] = message;
            putPosition = (putPosition + 1) % bufferSize;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        lock.lock();
        try {
            while (quantityOfElements == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException ex) {
                }
            }
            String res = buffer[getPosition];
            getPosition = (getPosition + 1) % bufferSize;
            quantityOfElements--;
            notFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }
}
