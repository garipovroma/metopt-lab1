package com.example.demo.model.optimizations;

public class Dichotomy {
    private final static double EPS = 1e-6;

    public double f(double x) {
        return 3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x);
    }

    public double run(double a, double b, double eps, double coef, boolean print) {
        int iter = 0;
        while (b - a > eps) {
            double delta = ((b - a) / 2) * coef;
            double x1 = (b + a - delta) / 2.0;
            double x2 = (b + a + delta) / 2.0;
            double fx1 = f(x1);
            double fx2 = f(x2);
            if (print) {
                System.out.println(iter + " " + a + " " + b + " " + x1 + " " + x2 + " " + fx1 + " " + fx2);
            }
            iter++;
            if (fx1 <= fx2 + EPS) {
                b = x2;
            } else {
                a = x1;
            }
        }
        double x = (a + b) / 2.0;
        double fx = f(x);
        return x;
    }
}
