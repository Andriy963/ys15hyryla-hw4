package ua.yandex.threadpool;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Andrii Hyryla
 */
public class ThreadPoolTest {

    private Set requestsSet;

    public void initOfSet() {
        requestsSet = new HashSet<String>();
    }
    
    private class TestThread implements Runnable {

        private final String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            requestsSet.add(name);
        }

    }

    @Test
    public void testThreadPollWithTenRequests() throws InterruptedException {
        int poolSize = 10;
        ThreadPool pool = new ThreadPool(poolSize);
        int quantityOfRequests = 20;
        TestThread[] requests = new TestThread[quantityOfRequests];
        initOfSet();
        
        Set expectResult = new HashSet<String>();
        for(int i = 0; i < quantityOfRequests; i++) {
            expectResult.add(Integer.toString(i));
        }
        
        for (int i =0; i < quantityOfRequests; i++) {
            requests[i] = new TestThread(Integer.toString(i));
            pool.addNewRequest(requests[i]);
        }
        pool.execute();
        Thread.sleep(1000);
        pool.shutdown();
        
        assertEquals(expectResult, requestsSet);
    }
    
    @Test
    public void testThreadPollWithHundredRequests() throws InterruptedException {
        int poolSize = 10;
        ThreadPool pool = new ThreadPool(poolSize);
        int quantityOfRequests = 20;
        TestThread[] requests = new TestThread[quantityOfRequests];
        initOfSet();
        
        Set expectResult = new HashSet<String>();
        for(int i = 0; i < quantityOfRequests; i++) {
            expectResult.add(Integer.toString(i));
        }
        
        for (int i =0; i < quantityOfRequests; i++) {
            requests[i] = new TestThread(Integer.toString(i));
            pool.addNewRequest(requests[i]);
        }
        pool.execute();
        Thread.sleep(1000);
        pool.shutdown();
        
        assertEquals(expectResult, requestsSet);
    }
}
