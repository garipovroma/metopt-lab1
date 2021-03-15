package com.example.demo.model.base;

import java.util.HashMap;

public class FibonacciCalculator {
    public static final HashMap<Integer, Double> fibonacciHashMap = new HashMap<Integer, Double>();
    private final static double sqrtOfFive = Math.sqrt(5.0);
    public static double fib(int n) {
        if (fibonacciHashMap.containsKey(n)) {
            return fibonacciHashMap.get(n);
        }
        double a = (1 + sqrtOfFive) / 2;
        double pow_a = Math.pow(a, n);
        double fib = (pow_a) / sqrtOfFive;
        fibonacciHashMap.put(n, fib);
        return fib;
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
