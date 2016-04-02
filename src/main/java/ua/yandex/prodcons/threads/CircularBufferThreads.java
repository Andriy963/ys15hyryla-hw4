package ua.yandex.prodcons.threads;

/**
 *
 * @author Andrii Hyryla
 */
public class CircularBufferThreads {
    private final String[] buffer;
    private final int bufferSize;
    private int quantityOfElements;
    private int putPosition;
    private int getPosition;

    public CircularBufferThreads(int bufferSize) {
        this.bufferSize = bufferSize;
        buffer = new String[bufferSize];
        for (int i = 0 ; i < bufferSize; i++) {
            buffer[i] = new String();
        }
    }
    
    public synchronized void put(String message) {
        while(quantityOfElements == bufferSize) {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        quantityOfElements++;
        buffer[putPosition] = message;
        putPosition = (putPosition + 1) % bufferSize;
        notifyAll();
    }
    
    public synchronized String get() {
        while(quantityOfElements == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        String res = buffer[getPosition];
        getPosition = (getPosition + 1) % bufferSize;
        quantityOfElements--;
        notifyAll();
        return res;
    }
    
}
