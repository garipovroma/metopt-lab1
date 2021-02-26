package com.example.demo.model.optimizations;

import java.util.Random;

public class Parabola {
    private final static double EPS = 1e-6;
    public double f(double x) {
        return -3.0 * x * Math.sin(0.75 * x) + Math.exp(-2.0 * x);
    }
    private double findInitialData(double l, double r) {
        double x1 = l, x3 = r, x2;
        Random random = new Random();
        for (int i = 0; i <= 100; i++) {
            x2 = random.nextDouble() * (r - l) + l;
            if (f(x2) <= f(x1) && f(x2) <= f(x3)) {
                return x2;
            }
        }
        throw new RuntimeException("Can't find x2");
    }
    public double run(double l, double r) {
        
    }
}
