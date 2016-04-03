package ua.yandex.lockfree;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Andrii Hyryla
 */
public class LockFree {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private final AtomicReference<BigInteger> numb 
            = new AtomicReference<>(BigInteger.ONE);
    
    public BigInteger next() {
        BigInteger current;
        BigInteger next;
        for(;;) {
            current = numb.get();
            next = current.multiply(TWO);
            if (numb.compareAndSet(current, next)) {
                return current;
            }
        }
    }
}
