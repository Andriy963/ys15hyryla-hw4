package ua.yandex.sumofseries;

import java.util.function.DoubleUnaryOperator;

/**
 *
 * @author Andrii Hyryla
 */
public class Calculate {

    private static final double STEP = 0.0001;
    private DoubleUnaryOperator f;

    public Calculate(DoubleUnaryOperator f) {
        this.f = f;
    }

    public double calculate(double start, double end) {
        double x = start;
        double sum = 0;
        while (x <= end) {
            sum += STEP * this.f.applyAsDouble(x);
            x += STEP;
            if (Thread.interrupted()) {
                return sum;
            }
        }
        return sum;
    }
}
