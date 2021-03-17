package com.example.demo.model.base;

import java.util.HashMap;

public class FibonacciCalculator {
    public static final HashMap<Integer, Double> FIBONACCI = new HashMap<>();
    private final static double SQRT_OF_FIVE = Math.sqrt(5.0);
    static {
        FIBONACCI.put(1, 1.0);
        FIBONACCI.put(2, 1.0);
    }
    public static double fib(int n) {
        return FIBONACCI.computeIfAbsent(n, num -> fib(num - 1) + fib(num - 2));
    }

    public static int calculateIterationsCount(double left, double right, double eps) {
        int iterations = 0;
        double frac = (right - left) / eps;
        while (fib(iterations + 2) < frac) {
            iterations++;
        }
        return iterations;
    }
}
