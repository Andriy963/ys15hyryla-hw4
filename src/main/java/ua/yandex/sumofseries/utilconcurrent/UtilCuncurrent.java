package ua.yandex.sumofseries.utilconcurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.DoubleUnaryOperator;
import ua.yandex.sumofseries.Calculate;

/**
 *
 * @author Andrii Hyryla
 */
public class UtilCuncurrent {
    
    public static final int MIN_M = 1;
    public static final int MAX_M = 8;

    public static final int N = 100;
    
    private Calculate c;

    public UtilCuncurrent(Calculate c) {
        this.c = c;
    }
    
    static class MyThread implements Callable<Double> {

        private final Calculate cal;
        private final double start;
        private final double end;
        private double result;

        public MyThread(Calculate cal, double start, double end) {
            this.cal = cal;
            this.start = start;
            this.end = end;
        }

        @Override
        public Double call() throws Exception {
            result = cal.calculate(-N, N);
            return result;
        }
    }
    
    private void countSum(int M) throws InterruptedException, 
            ExecutionException {
        double startTime = System.currentTimeMillis();
        MyThread[] mth = new MyThread[M];
        Future<Double>[] f = new Future[M];
        
        ExecutorService es = Executors.newFixedThreadPool(M);
        for (int i = 0; i < M; i++) {
            double start = -N + 2.0 * N * i / M;
            double end = -N + 2.0 * N * (i + 1) / M;
            mth[i] = new MyThread(c, start, end);
            f[i] = es.submit(mth[i]);
        }
        double res = 0;
        for (int i = 0; i < M; i++) {
            res += f[i].get();
        }
        System.out.println(M + " threads:");
        System.out.println(res);

        double endTime = System.currentTimeMillis();
        System.out.println("work time: " + (endTime - startTime));
    }
    
    public static void main(String[] args) throws InterruptedException, 
            ExecutionException {
        DoubleUnaryOperator f = new DoubleUnaryOperator() {

            @Override
            public double applyAsDouble(double x) {
                return Math.sin(x) * Math.cos(x);
            }
        };
        Calculate c = new Calculate(f);
        
        UtilCuncurrent utCun = new UtilCuncurrent(c);
        
        for (int i = MIN_M; i <= MAX_M; i++) {
            utCun.countSum(i);
        }
    }
}
