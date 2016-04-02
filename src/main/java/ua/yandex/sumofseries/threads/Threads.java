package ua.yandex.sumofseries.threads;

import java.util.function.DoubleUnaryOperator;
import ua.yandex.sumofseries.Calculate;

/**
 *
 * @author Andrii Hyryla
 */
public class Threads {

    public static final int MIN_M = 1;
    public static final int MAX_M = 8;

    public static final int N = 100;

    private Calculate c;

    public Threads(Calculate c) {
        this.c = c;
    }
    
    static class MyThread implements Runnable {

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
        public void run() {
            result = cal.calculate(-N, N);
        }

        public double getResult() {
            return result;
        }
    }

    private void countSum(int M) throws InterruptedException {
        double startTime = System.currentTimeMillis();
        Thread[] th = new Thread[M];
        MyThread[] mth = new MyThread[M];

        for (int i = 0; i < M; i++) {
            double start = -N + 2.0 * N * i / M;
            double end = -N + 2.0 * N * (i + 1) / M;
            mth[i] = new MyThread(c, start, end);
            th[i] = new Thread(mth[i]);
        }
        for (int i = 0; i < M; i++) {
            th[i].start();
        }
        for (int i = 0; i < M; i++) {
            th[i].join();
        }

        double res = 0;
        for (int i = 0; i < M; i++) {
            res += mth[i].getResult();
        }
        System.out.println(M + " threads:");
        System.out.println(res);

        double endTime = System.currentTimeMillis();
        System.out.println("work time: " + (endTime - startTime));
    }

    public static void main(String[] args) throws InterruptedException {
        DoubleUnaryOperator f = new DoubleUnaryOperator() {

            @Override
            public double applyAsDouble(double x) {
                return Math.sin(x) * Math.cos(x);
            }
        };
        Calculate c = new Calculate(f);
        
        Threads threads = new Threads(c);

        for (int i = MIN_M; i <= MAX_M; i++) {
            threads.countSum(i);
        }
    }

}
