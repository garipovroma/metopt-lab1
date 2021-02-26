package com.example.demo.model.optimizations;

import java.util.Random;

public class Parabola {
    private final static double EPS = 1e-6;
    public double f(double x) {
        return -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x);
    }
    private int compare(double x, double y) {
        return Double.compare(x, y);
    }
    private int compareWithEps(double x, double y) {
        if (Math.abs(x - y) <= -EPS) {
            return -1;
        } else if (Math.abs(x - y) > EPS) {
            return 1;
        }
        return 1;
    }
    private double findInitialPoint(double l, double r) {
        double x1 = l, x3 = r, x2;
        Random random = new Random();
        for (int i = 0; i <= 100; i++) {
            x2 = random.nextDouble() * (r - l) + l;
            if (compare(f(x2), f(x1)) <= 0 && compare(f(x2), f(x3)) <= 0) {
                return x2;
            }
        }
        throw new RuntimeException("Can't find x2");
    }
    private double findParabolaMinX(double x1, double x2, double x3) {
        double f1 = f(x1), f2 = f(x2), f3 = f(x3);
        double a0 = f1, a1 = (f2 - f1) / (x2 - x1),
                a2 = ( (x3 - f1) / (x3 - x1) - (f2 - f1) / (x2 - x1) ) / (x3 - x2);
        return (x1 + x2 - a1 / a2) / 2;
    }
    public double run(double l, double r) {
        double x1 = l, x2 = findInitialPoint(l, r), x3 = r;
        int iter = 0;
        boolean firstIteration = true;
        double minX = Double.NaN;
        double prevIterationMinX = Double.NaN;
        while (true) {
            double f1 = f(x1), f2 = f(x2), f3 = f(x3);
            double pMinX = findParabolaMinX(x1, x2, x3);
            double pMinY = f(pMinX);

            System.out.println(iter + " " + l + " " + r + " " +
                    x1 + " " + x2 + " " + x3 + " " +
                    f1 + " " + f2 + " " + f3 + " " +
                    pMinX + " " + pMinY);

            if (!firstIteration) {
                if (compareWithEps(pMinX, prevIterationMinX) < 0) {
                    minX = pMinX;
                    break;
                }
            } else {
                firstIteration = false;
            }

            // bounds calc
            {
                // pMin = ParabolaMinY, pMinx = ParabolaMinX
                if (compare(x1, pMinX) <= 0 && compare(pMinX, x2) < 0) { // x1 <= pMinX < x2
                    if (compare(pMinY, f2) >= 0) { // pMin >= f(x2)
                        x1 = pMinX;
                    } else { // pMin < f(x2)
                        x3 = x2;
                        x2 = pMinX;
                    }
                } else if (compare(x2, pMinX) <= 0 && compare(pMinX, x3) <= 0) { // x2 <= pMinX <= x3
                    if (compare(f2, pMinY) >= 0) { // f(x2) >= pMin
                        x1 = x2;
                        x2 = pMinX;
                    } else { // f(x2) < pMin
                        x3 = pMinX;
                    }
                }
            }
            // Now x1, x2, x3 are new bounds
            prevIterationMinX = pMinX;
            iter++;
        }
        double minx = prevIterationMinX;
        return minX;
    }

}
