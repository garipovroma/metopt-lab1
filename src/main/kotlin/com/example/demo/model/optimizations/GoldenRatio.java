package com.example.demo.model.optimizations;

public class GoldenRatio {
    private final double tau = (Math.sqrt(5.0) - 1.0) / 2.0;

    public double f(double x) {
        return -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x);
    }

    public double run(double a, double b, double eps, boolean print) {
        int iter = 0;
        double x1 = a + (1.0 - tau) * (b - a);
        double x2 = a + tau * (b - a);
        double fx1 = f(x1);
        double fx2 = f(x2);
        while (b - a > eps) {
            if (fx1 <= fx2) {
                b = x2;
                x2 = x1;
                fx2 = fx1;
                x1 = b - tau * (b - a);
                fx1 = f(x1);
            } else {
                a = x1;
                x1 = x2;
                fx1 = fx2;
                x2 = a + tau * (b - a);
                fx2 = f(x2);
            }
            if (print) {
                System.out.println(iter++ + " " + a + " " + b + " " + x1 + " " + x2 + " " + fx1 + " " + fx2 + " " + (b - a));
            }
        }
        double x = (a + b) / 2.0;
        double fx = f(x);
        return x;
    }
}
