package ua.yandex.threadpool;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andrii Hyryla
 */
public class ThreadPool {

    private final int poolSize;
    private final Thread[] pool;
    private final PoolThread[] poolThread;
    private final List<Runnable> queue
            = Collections.synchronizedList(new LinkedList<Runnable>());

    public ThreadPool(int poolSize) {
        this.poolSize = poolSize;
        pool = new Thread[poolSize];
        poolThread = new PoolThread[poolSize];
        for (int i = 0; i < poolSize; i++) {
            poolThread[i] = new PoolThread();
            pool[i] = new Thread(poolThread[i]);
        }
    }

    public synchronized void addNewRequest(Runnable request) {
        queue.add(request);
    }

    public void execute() {
        for (int i = 0; i < poolSize; i++) {
            pool[i].start();
            poolThread[i].setIsRunning(true);
        }

    }

    public void shutdown() throws InterruptedException {
        for (int i = 0; i < poolSize; i++) {
            poolThread[i].setIsRunning(false);
        }
    }

    private class PoolThread implements Runnable {

        private boolean isRunning = true;

        @Override
        public void run() {
            while (isRunning) {
                if (!queue.isEmpty()) {
                    Runnable request = queue.get(0);
                    queue.remove(0);
                    if (request != null) {
                        request.run();
                    }
                }
            }
        }

        public boolean isActive() {
            return isRunning;
        }

        public void setIsRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }
}
