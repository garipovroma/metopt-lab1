package com.example.demo.model.base;

import java.util.HashMap;

public class FibonacciCalculator {
    public static final HashMap<Integer, Double> FIBONACCI = new HashMap<>();
    private final static double SQRT_OF_FIVE = Math.sqrt(5.0);
    public static double fib(int n) {
//        if (FIBONACCI.containsKey(n)) {
//            return FIBONACCI.get(n);
//        }
        return FIBONACCI.computeIfAbsent(n, num -> Math.pow((1 + SQRT_OF_FIVE) / 2, num) / SQRT_OF_FIVE);
//        double a = (1 + SQRT_OF_FIVE) / 2;
//        double pow_a = Math.pow(a, n);
//        double fib = (pow_a) / SQRT_OF_FIVE;
//        FIBONACCI.put(n, fib);
//        return fib;
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
